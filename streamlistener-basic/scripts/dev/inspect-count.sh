list=(xformed-json1 xformed-avro1 xformed-proto1 xformed-raw)
for topic in ${list[@]}; do
    kafka-run-class kafka.tools.GetOffsetShell --broker-list b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic $topic
done
