for i in {1..10}; do
    printf .
    kafka-console-producer --bootstrap-server localhost:9092 --topic testtock-json1 < payload-5x.json > /dev/null
    kafka-protobuf-console-producer --bootstrap-server localhost:9092 --topic testtock-proto1 --property value.schema.file=src/main/resources/gpi-schema.proto < payload-5x.json
    kafka-avro-console-producer --bootstrap-server localhost:9092 --topic testtock-avro1 --property value.schema.file=src/main/resources/gpi-schema.avsc < payload-5x.json
done
