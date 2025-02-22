package com.example.monitorsensors.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.monitorsensors.dto.SensorRequestDTO;
import com.example.monitorsensors.dto.SensorResponseDTO;
import com.example.monitorsensors.service.SensorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SensorResponseDTO> createSensor(
            @Valid @RequestBody SensorRequestDTO sensorDTO) {
                System.out.println("sensorDTO: " + sensorDTO.getName() +
                " " + sensorDTO.getModel() + " " + " " + sensorDTO.getType() +
                " " + sensorDTO.getUnit() + " " + sensorDTO.getLocation() +
                " " + sensorDTO.getDescription());

        SensorResponseDTO createdSensor = sensorService.createSensor(sensorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    public ResponseEntity<List<SensorResponseDTO>> getAllSensors(
            @RequestParam(required = false) String search) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(sensorService.searchSensors(search));
        }
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    public ResponseEntity<SensorResponseDTO> getSensorById(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensorById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<SensorResponseDTO> updateSensor(
            @PathVariable Long id,
            @Valid @RequestBody SensorRequestDTO sensorDTO) {
                System.out.println("sensorDTO: " + sensorDTO.getName() +    
                " " + sensorDTO.getModel() + " " + " " + sensorDTO.getType() +
                " " + sensorDTO.getUnit() + " " + sensorDTO.getLocation() +
                " " + sensorDTO.getDescription());
                
        return ResponseEntity.ok(sensorService.updateSensor(id, sensorDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}