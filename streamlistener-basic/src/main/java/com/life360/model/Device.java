package com.life360.model;

@lombok.Data
public class Device {

    private int discovery_timestamp;

    private String pduPayload;

    private int pduRssi;
}
