for i in {1..4}; do
    printf .
    kafka-console-producer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic testtock-json1 < payload250k.json > /dev/null
    kafka-protobuf-console-producer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic testtock-proto1 --property value.schema.file=gpi-schema.proto --property schema.registry.url=http://localhost:8081 < payload250k.json
    kafka-avro-console-producer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic testtock-avro1 --property value.schema.file=gpi-schema.avsc --property schema.registry.url=http://localhost:8081 < payload250k.json
done
