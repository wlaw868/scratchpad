package com.life360.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(AvroRouter.Source.class)
public class AvroRouter {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener("avro1")
	@SendTo(Processor.OUTPUT)
	public com.life360.gpi.schema.avro.Message receive(com.life360.gpi.schema.avro.Message msg) {
		return msg;
	}

    public interface Source {
        String SAMPLE = "avro1";
        @Input(SAMPLE)
        MessageChannel sampleSource();
    }
}
