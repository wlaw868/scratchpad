package com.life360.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(ProtoTransformer.Source.class)
public class ProtoTransformer {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener("proto")
	@SendTo(Processor.OUTPUT)
	public com.baeldung.schema.proto.Employee receive(com.baeldung.schema.proto.Employee emp) {
		return com.baeldung.schema.proto.Employee
            .newBuilder(emp)
            .setFirstName(SampleTransformer.shuffle(emp.getFirstName()))
            .build();
	}

    public interface Source {
        String SAMPLE = "proto";
        @Input(SAMPLE)
        MessageChannel sampleSource();
    }
}
