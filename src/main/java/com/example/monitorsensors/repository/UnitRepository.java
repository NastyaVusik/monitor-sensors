package com.example.monitorsensors.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.monitorsensors.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findByName(String name);
}
