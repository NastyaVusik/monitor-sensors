package com.example.monitorsensors.repository;
  

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.monitorsensors.model.SensorType;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {
    Optional<SensorType> findByName(String name);
}

