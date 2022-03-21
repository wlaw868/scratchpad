package com.life360.model;

import java.util.Map;

@lombok.Data
public class Data {

    private Location location;

    private Map<String,ListOfDevice> devices;
}
