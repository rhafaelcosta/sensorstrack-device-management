package com.github.rhafaelcosta.sensorstrack.device.management.api.client;

import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorMonitoringResponse;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enableMonitoring(TSID sensorId);

    void disableMonitoring(TSID sensorId);

    SensorMonitoringResponse getDetail(TSID sensorId);
}
