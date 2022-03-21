LAST_OFFSET=999999
START=`kafka-protobuf-console-consumer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic xformed-proto1 --property print.timestamp=true --offset 0 --partition 0 --max-messages 1 | awk -F '[:{]' '{print $2}'` 
END=`kafka-protobuf-console-consumer --bootstrap-server b-3.dev-kafka-services-de.944560.c13.kafka.us-east-1.amazonaws.com:9092 --topic xformed-proto1 --property print.timestamp=true --offset $LAST_OFFSET --partition 0 --max-messages 1 | awk -F '[:{]' '{print $2}'`
DIFF=$(expr $END - $START)
echo $DIFF " MS"
