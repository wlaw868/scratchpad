LAST_OFFSET=999999
kafka-console-consumer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic xformed-bytes1 --property print.timestamp=true --offset 0 --partition 0 --max-messages 1
kafka-console-consumer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic xformed-bytes1 --property print.timestamp=true --offset $LAST_OFFSET --partition 0 --max-messages 1
