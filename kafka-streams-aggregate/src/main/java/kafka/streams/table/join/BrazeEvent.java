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

import java.util.Collection;

@lombok.Data
public class BrazeEvent {

    String event;
    String circleId;
    int tileCount;
    String tileOwnerId;
    Collection<Tile> tiles;

    public BrazeEvent(String event, String circleId, String tileOwnerId, Collection<Tile> tiles) {
        this.event = event;
        this.circleId = circleId;
        this.tileCount = tiles.size();
        this.tileOwnerId = tileOwnerId;
        this.tiles = tiles;
    }
}
