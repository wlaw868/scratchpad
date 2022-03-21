list=(xformed-json1 xformed-avro1 xformed-proto1 xformed-raw)
for topic in ${list[@]}; do
    kafka-topics --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic $topic --delete
    kafka-topics --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic $topic --partitions 1 --create
done
