package com.github.rhafaelcosta.sensorstrack.device.management.api.controller;


import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorRequest;
import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorResponse;
import com.github.rhafaelcosta.sensorstrack.device.management.common.IdGenerator;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.Sensor;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.SensorId;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponse create(@RequestBody SensorRequest request) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(request.getName())
                .ip(request.getIp())
                .location(request.getLocation())
                .protocol(request.getProtocol())
                .model(request.getModel())
                .enabled(false)
                .build();

        sensor = sensorRepository.saveAndFlush(sensor);
        return SensorResponse.builder()
                .id(sensor.getId().getValue())
                .ip(sensor.getIp())
                .name(sensor.getName())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .protocol(sensor.getProtocol())
                .location(sensor.getLocation())
                .build();
    }

}