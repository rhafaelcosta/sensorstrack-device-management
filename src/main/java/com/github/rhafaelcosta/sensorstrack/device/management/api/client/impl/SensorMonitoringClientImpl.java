package com.github.rhafaelcosta.sensorstrack.device.management.api.client.impl;

import com.github.rhafaelcosta.sensorstrack.device.management.api.client.RestClientFactory;
import com.github.rhafaelcosta.sensorstrack.device.management.api.client.SensorMonitoringClient;
import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorMonitoringResponse;
import io.hypersistence.tsid.TSID;
import org.springframework.web.client.RestClient;

public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClientFactory factory) {
        this.restClient = factory.temperatureMonitoringRestClient();
    }

    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient
                .put()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void disableMonitoring(TSID sensorId) {
        restClient
                .delete()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public SensorMonitoringResponse getDetail(TSID sensorId) {
        return restClient
                .get()
                .uri("/api/sensors/{sensorId}/monitoring", sensorId)
                .retrieve()
                .body(SensorMonitoringResponse.class);
    }

}
