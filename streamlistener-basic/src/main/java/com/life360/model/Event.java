package com.life360.model;

import java.util.Map;

@lombok.Data
public class Event {

    private String id;

    private String time;

    private String source;

    private String type;

    private String subject;

    private Map<String,Data> data;
}
