package com.life360.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(RawRouter.Source.class)
public class RawRouter {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener("raw")
	@SendTo(Processor.OUTPUT)
	public byte[] receive(byte[] msg) {
		return msg;
	}

    public interface Source {
        String SAMPLE = "raw";
        @Input(SAMPLE)
        MessageChannel sampleSource();
    }
}
