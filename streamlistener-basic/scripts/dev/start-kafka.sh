rm -rf /tmp/kafka-logs /tmp/zookeeper
zookeeper-server-start confluent-6.1.0/etc/kafka/zookeeper.properties > /dev/null &
kafka-server-start confluent-6.1.0/etc/kafka/server.properties > /dev/null &
sleep 5
schema-registry-start confluent-6.1.0/etc/schema-registry/schema-registry.properties
