package com.life360.demo;

import com.baeldung.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
public class SampleTransformer {

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Employee receiveJson(Employee emp) {
        return new Employee(emp.getId(), shuffle(emp.getFirstName()), emp.getLastName(), emp.getEmail());
	}

    public static String shuffle(final String str) {
        List<Character> chars = str.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
        Collections.shuffle(chars);
        return chars.stream().map(e -> e.toString()).collect(Collectors.joining());
    }
}
