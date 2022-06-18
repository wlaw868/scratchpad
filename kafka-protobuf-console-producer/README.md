# Kafka Protobuf Console Producer Wrapper

It shares the same syntax as the kafka-protobuf-console-producer (Confluent schema registry tool) plus the ability to write headers using json payload.

## Required
Maven, Java 11+

## Build
```
% mvn clean package
```

## Usage
```
% java -jar target/kafka-protobuf-console-producer.jar --bootstrap-server localhost:9092 --topic test-topic --property schema.registry.url=http://localhost:8081 --property value.schema.file=./blev1.proto --property parse.headers=true < samples/tile_ble_filtered.json
```
