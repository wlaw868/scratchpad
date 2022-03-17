# Basic Stream Listener

## Description

This is a Kafka producer and consumer implementation using Spring Cloud Stream.

It produces test message to 'testtock' topic every second, transforms the first name, and produces to 'xformed' topic.

## Getting Started

To update schema, edit:

* Json: src/main/java/com/baeldung/model/Employee.java 
* Avro: src/main/resources/employee-schema.avsc
* Protobuf: src/main/resources/employee-schema.proto

To build:

```
% mvn clean package
```

### Dependencies

* Java 11
* Maven
* Docker

### Executing program

Bring up Kafka cluster.

```
% docker-compose up -d
% docker-compose -f docker-compose-m1.yml up -d (if you have an M1!)
```

Shutdown Kafka cluster.
```
% docker-compose down
```

Produce Json payload.
```
% java -jar target/streamlistener-basic-0.0.1-SNAPSHOT-kafka.jar
```

Produce Avro payload.
```
% java -jar target/streamlistener-basic-0.0.1-SNAPSHOT-kafka.jar --spring.profiles.active=avro
```

Produce Protobuf payload.
```
% java -jar target/streamlistener-basic-0.0.1-SNAPSHOT-kafka.jar --spring.profiles.active=proto
```

Consume output topics.
```
% kafka-console-consumer --bootstrap-server localhost:9092 --topic xformed --from-beginning
% kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic xformed-avro --from-beginning
% kafka-protobuf-console-consumer --bootstrap-server localhost:9092 --topic xformed-proto --from-beginning
```

## Authors

Event-gers (event systems)

