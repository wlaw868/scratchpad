package com.life360.model;

@lombok.Data
public class Location {

    private float latitude;

    private float longitude;

    private float altitude;

    private float speed;

    private int time;

    private int elapsedRealtimeNanos;

    private float horizontalAccuracy;

    private float verticalAccuracy;

    private float speedAccuracy;

    private float course;

    private float courseAccuracy;
}
