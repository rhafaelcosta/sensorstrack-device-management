package com.github.rhafaelcosta.sensorstrack.device.management.domain.repository;


import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.Sensor;
import com.github.rhafaelcosta.sensorstrack.device.management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}