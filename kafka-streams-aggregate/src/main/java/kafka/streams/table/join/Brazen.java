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

@lombok.Data
public class Brazen {

	String userId;
    String integrationId;
    Tile tile;
    boolean tileAdded;
    boolean tileRemoved;
    boolean accountLinked;
    boolean accountUnlinked;

    public Brazen() {}

    public Brazen(String userId, String integrationId) {
        this.userId = userId;
        this.integrationId = integrationId;
    }

    public Brazen(TileAdded v) {
        this(v.getUserId(), v.getIntegrationId());
        this.tile = new Tile(v.getTileName(), v.getTileCategory(), v.getTileHardwareModel());
        this.tileAdded = true;
    }

    public Brazen(TileRemoved v) {
        this(v.getUserId(), v.getIntegrationId());
        this.tile = new Tile(v.getTileName(), v.getTileCategory(), v.getTileHardwareModel());
        this.tileRemoved = true;
    }

    public Brazen(AccountLinked v) {
        this(v.getUserId(), v.getIntegrationId());
        this.accountLinked = true;
    }

    public Brazen(AccountUnlinked v) {
        this(v.getUserId(), v.getIntegrationId());
        this.accountUnlinked = true;
    }
}
