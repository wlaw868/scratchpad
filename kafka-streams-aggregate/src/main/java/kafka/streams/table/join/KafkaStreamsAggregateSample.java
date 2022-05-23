/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kafka.streams.table.join;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.To;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@lombok.extern.log4j.Log4j2
@EnableBinding(MessageBinding.class)
@SpringBootApplication
public class KafkaStreamsAggregateSample {

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsAggregateSample.class, args);
    }

    @Bean
    public StoreBuilder aggregateStore() {
        return Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore("aggregate"), Serdes.String(), new JsonSerde<>(Aggregate.class, mapper));
    }

    @Component
    public static class KafkaStreamConsumer {

        @Value("${window.size}")
        int windowSizeMs;

        @StreamListener
        @SendTo("brazeEvent")
        public KStream<String,BrazeEvent> aggregate(
            @Input("tileAdded") KStream<String,TileAdded> tileAddedStream,
            @Input("tileRemoved") KStream<String,TileRemoved> tileRemovedStream,
            @Input("accountLinked") KStream<String,AccountLinked> accountLinkedStream,
            @Input("accountUnlinked") KStream<String,AccountUnlinked> accountUnlinkedStream) {

            return tileAddedStream
                .mapValues(v -> new Brazen(v))
                .merge(tileRemovedStream.mapValues(v -> new Brazen(v)))
                .merge(accountLinkedStream.mapValues(v -> new Brazen(v)))
                .merge(accountUnlinkedStream.mapValues(v -> new Brazen(v)))
                .peek((k,v) -> log.info("---- Peek {} {}", k, v))
                .selectKey((k,v) -> v.getUserId() + v.getIntegrationId())
                .repartition()
                .transform(() -> new Transformer<String,Brazen,KeyValue<String,BrazeEvent>>() {

                    KeyValueStore<String,Aggregate> aggregateStore;

                    @Override
                    public void init(ProcessorContext context) {
                        aggregateStore = (KeyValueStore<String,Aggregate>) context.getStateStore("aggregate");
                        context.schedule(Duration.ofMillis(windowSizeMs), PunctuationType.WALL_CLOCK_TIME, timestamp -> {
                            log.info("---- Punctuate {}", timestamp);
                            List<String> keys = new ArrayList<>(); 
                            List<BrazeEvent> events = new ArrayList<>();
                            aggregateStore.all().forEachRemaining(pair -> {
                                keys.add(pair.key);
                                Aggregate result = pair.value;
                                if (result.getAddedTiles() != null) {
                                    String event = result.isAccountLinked() ? "tile-connected-circle-link" : "tile-connected";
                                    events.add(new BrazeEvent(event, result.getUserId(), result.getIntegrationId(), result.getAddedTiles().values()));
                                }
                                if (result.getRemovedTiles() != null) {
                                    String event = result.isAccountUnlinked() ? "tile-disconnected-circle-unlink" : "tile-disconnected";
                                    events.add(new BrazeEvent(event, result.getUserId(), result.getIntegrationId(), result.getRemovedTiles().values()));
                                }
                            });
                            keys.forEach(v -> aggregateStore.put(v, null));
                            events.forEach(v -> {
                                log.info("---- Emit {}", v);
                                context.forward(null, v, To.all());
                            });
                        });
                    }

                    @Override
                    public KeyValue<String,BrazeEvent> transform(String key, Brazen value) {
                        log.info("---- Transform {} {}", key, value);
                        Aggregate result = aggregateStore.get(key);
                        if (result == null) {
                            result = new Aggregate(value.getUserId(), value.getIntegrationId());
                        }
                        if (value.isTileAdded()) {
                            result.addTile(value.getTile());
                        }
                        if (value.isTileRemoved()) {
                            result.removeTile(value.getTile());
                        }
                        if (value.isAccountLinked()) {
                            result.linkAccount();
                        }
                        if (value.isAccountUnlinked()) {
                            result.unlinkAccount();
                        }
                        log.info("---- Store {} {}", key, result);
                        aggregateStore.put(key, result);
                        return null;
                    }

                    @Override
                    public void close() {}

                }, "aggregate");
        }
    }
}
