list=(xformed-json1 xformed-avro1 xformed-proto1 xformed-raw)
for topic in ${list[@]}; do
    kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic $topic
done
