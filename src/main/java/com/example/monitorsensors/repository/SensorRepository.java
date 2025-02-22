package com.example.monitorsensors.repository;
  

import com.example.monitorsensors.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(String name, String model);
}
