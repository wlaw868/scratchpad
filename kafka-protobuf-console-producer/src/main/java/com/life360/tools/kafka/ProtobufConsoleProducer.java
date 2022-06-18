package com.life360.tools.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import kafka.tools.ConsoleProducer;

public class ProtobufConsoleProducer {

    public static void main(String[] args) throws Exception {
        List<String> moreArgs = List.of(
                "--line-reader",
                "io.confluent.kafka.formatter.protobuf.ProtobufMessageReader");
        ConsoleProducer.main(Stream.of(Arrays.asList(args), moreArgs).flatMap(List::stream).toArray(String[]::new));
    }
}
