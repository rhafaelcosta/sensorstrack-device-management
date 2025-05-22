package com.github.rhafaelcosta.sensorstrack.device.management.api.client;

import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorMonitoringResponse;
import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.*;

@HttpExchange("/api/sensors/{sensorId}/monitoring")
public interface SensorMonitoringClient {

    @PutExchange("/enable")
    void enableMonitoring(@PathVariable TSID sensorId);

    @DeleteExchange("/enable")
    void disableMonitoring(@PathVariable TSID sensorId);

    @GetExchange
    SensorMonitoringResponse getDetail(@PathVariable TSID sensorId);
}
