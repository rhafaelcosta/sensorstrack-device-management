package com.github.rhafaelcosta.sensorstrack.device.management.api.model;

import lombok.Data;

@Data
public class SensorRequest {
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
}