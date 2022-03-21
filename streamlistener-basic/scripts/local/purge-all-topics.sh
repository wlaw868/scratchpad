list=(testtock-json1 testtock-avro1 testtock-proto1 xformed-json1 xformed-avro1 xformed-proto1 xformed-raw)
for topic in ${list[@]}; do
    kafka-topics --bootstrap-server localhost:9092 --topic $topic --delete
    kafka-topics --bootstrap-server localhost:9092 --topic $topic --partitions 1 --create
done
