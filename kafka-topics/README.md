% java -jar kafka-topics.jar --bootstrap-server b-2.dev-kafka-iam-dev-kaf.mbwjkk.c22.kafka.us-east-1.amazonaws.com:9098 --command-config ./client.properties --create --partitions 32 --replication-factor 3 --topic tile_ble_filtered

% java -jar kafka-topics.jar --bootstrap-server b-2.dev-kafka-iam-dev-kaf.mbwjkk.c22.kafka.us-east-1.amazonaws.com:9098 --command-config ./client.properties --list
