package com.life360.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(JsonRouter.Source.class)
public class JsonRouter {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener("json1")
	@SendTo(Processor.OUTPUT)
	public com.life360.model.Message receive(com.life360.model.Message msg) {
		return msg;
	}

    public interface Source {
        String SAMPLE = "json1";
        @Input(SAMPLE)
        MessageChannel sampleSource();
    }
}
