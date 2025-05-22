package com.github.rhafaelcosta.sensorstrack.device.management.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDetailResponse {

    private SensorResponse sensor;
    private SensorMonitoringResponse monitoring;

}
