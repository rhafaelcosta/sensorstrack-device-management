package com.github.rhafaelcosta.sensorstrack.device.management.api.controller;


import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorRequest;
import com.github.rhafaelcosta.sensorstrack.device.management.api.model.SensorResponse;
import com.github.rhafaelcosta.sensorstrack.device.management.common.IdGenerator;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.Sensor;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.SensorId;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    @GetMapping
    public Page<SensorResponse> search(@PageableDefault Pageable pageable) {
        Page<Sensor> sensors = sensorRepository.findAll(pageable);
        return sensors.map(this::convertToModel);
    }

    @GetMapping("{sensorId}")
    public SensorResponse findById(@PathVariable TSID sensorId) {
        Sensor sensor = this.sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToModel(sensor);
    }

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
        return convertToModel(sensor);
    }

    @PutMapping("/{sensorId}")
    public SensorResponse update(@PathVariable TSID sensorId, @RequestBody SensorRequest request) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensor.setName(request.getName());
        sensor.setLocation(request.getLocation());
        sensor.setIp(request.getIp());
        sensor.setModel(request.getModel());
        sensor.setProtocol(request.getProtocol());

        sensor = sensorRepository.save(sensor);
        return convertToModel(sensor);
    }



    private SensorResponse convertToModel(Sensor sensor) {
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