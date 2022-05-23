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

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface MessageBinding {

    @Input
    KStream<String,TileAdded> tileAdded();

    @Input
    KStream<String,TileRemoved> tileRemoved();

    @Input
    KStream<String,AccountLinked> accountLinked();

    @Input
    KStream<String,AccountUnlinked> accountUnlinked();

    @Output
    KStream<String,BrazeEvent> brazeEvent();
}
