package com.life360.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(AvroTransformer.Source.class)
public class AvroTransformer {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener("avro")
	@SendTo(Processor.OUTPUT)
	public com.baeldung.schema.avro.Employee receive(com.baeldung.schema.avro.Employee emp) {
		return com.baeldung.schema.avro.Employee
            .newBuilder(emp)
            .setFirstName(SampleTransformer.shuffle(emp.getFirstName().toString()))
            .build();
	}

    public interface Source {
        String SAMPLE = "avro";
        @Input(SAMPLE)
        MessageChannel sampleSource();
    }
}
