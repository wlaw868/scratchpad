package com.life360.demo;

import com.baeldung.model.Employee;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(SampleSource.Source.class)
public class SampleSource {

	private final Log logger = LogFactory.getLog(getClass());
    private final Random rand = new Random();
    private final String names[] = new String[] {"Abbey","Alexia","Alysia","Bella","Carleen","Deborah","Eddie","Faye","Giulia","Hana","Elva","Izawa","Karl","Lowson","Matheson","Mosley","Owen","Peyton"};

	@Bean
    @ConditionalOnProperty(name="payload", havingValue="proto")
	@InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
	public MessageSource<com.baeldung.schema.proto.Employee> protoMessageSource() {
		return new MessageSource<com.baeldung.schema.proto.Employee>() {
			public Message<com.baeldung.schema.proto.Employee> receive() {
                String firstName = names[rand.nextInt(names.length)];
                String lastName = names[rand.nextInt(names.length)];
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@life360.com";
                com.baeldung.schema.proto.Employee e = com.baeldung.schema.proto.Employee
                    .newBuilder()
                    .setId(rand.nextInt(Integer.MAX_VALUE))
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .build();
				return MessageBuilder.withPayload(e).build();
			}
		};
	}

	@Bean
    @ConditionalOnProperty(name="payload", havingValue="avro")
	@InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
	public MessageSource<com.baeldung.schema.avro.Employee> avroMessageSource() {
		return new MessageSource<com.baeldung.schema.avro.Employee>() {
			public Message<com.baeldung.schema.avro.Employee> receive() {
                String firstName = names[rand.nextInt(names.length)];
                String lastName = names[rand.nextInt(names.length)];
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@life360.com";
                com.baeldung.schema.avro.Employee e = com.baeldung.schema.avro.Employee
                    .newBuilder()
                    .setId(rand.nextInt(Integer.MAX_VALUE))
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .build();
				return MessageBuilder.withPayload(e).build();
			}
		};
	}

	@Bean
    @ConditionalOnProperty(name="payload", havingValue="json")
	@InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
	public MessageSource<Employee> jsonMessageSource() {
		return new MessageSource<Employee>() {
			public Message<Employee> receive() {
                String firstName = names[rand.nextInt(names.length)];
                String lastName = names[rand.nextInt(names.length)];
                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@life360.com";
                Employee e = new Employee(rand.nextInt(Integer.MAX_VALUE), firstName, lastName, email);
				return MessageBuilder.withPayload(e).build();
			}
		};
	}

	public interface Source {
		String SAMPLE = "sample-source";
		@Output(SAMPLE)
		MessageChannel sampleSource();
	}
}
