package com.example.monitorsensors.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.monitorsensors.dto.SensorRequestDTO;
import com.example.monitorsensors.dto.SensorResponseDTO;
import com.example.monitorsensors.exception.ResourceNotFoundException;
import com.example.monitorsensors.mapper.SensorMapper;
import com.example.monitorsensors.model.Sensor;
import com.example.monitorsensors.model.SensorType;
import com.example.monitorsensors.model.Unit;
import com.example.monitorsensors.repository.SensorRepository;
import com.example.monitorsensors.repository.SensorTypeRepository;
import com.example.monitorsensors.repository.UnitRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorTypeRepository sensorTypeRepository;
    private final UnitRepository unitRepository;
    private final SensorMapper sensorMapper;

    public SensorService(SensorRepository sensorRepository,
            SensorTypeRepository sensorTypeRepository,
            UnitRepository unitRepository,
            SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorTypeRepository = sensorTypeRepository;
        this.unitRepository = unitRepository;
        this.sensorMapper = sensorMapper;
    }

    public SensorResponseDTO createSensor(SensorRequestDTO dto) {
        Sensor sensor = sensorMapper.toEntity(dto);

        SensorType type = sensorTypeRepository.findByName(dto.getType())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor " + dto.getType() + " not found"));
        sensor.setType(type);

        if (dto.getUnit() != null) {
            Unit unit = unitRepository.findByName(dto.getUnit())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit " + dto.getUnit() + " not found"));
            sensor.setUnit(unit);
        }

        return sensorMapper.toDto(sensorRepository.save(sensor));
    }

    public List<SensorResponseDTO> getAllSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<SensorResponseDTO> searchSensors(String searchTerm) {
        return sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(searchTerm.toLowerCase(), searchTerm.toLowerCase()).stream()
                .map(sensorMapper::toDto)
                .collect(Collectors.toList());
    }

    public SensorResponseDTO getSensorById(Long id) {
        return sensorRepository.findById(id)
                .map(sensorMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor " + id + " not found"));
    }

    public SensorResponseDTO updateSensor(Long id, SensorRequestDTO dto) {
        Sensor existingSensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor " + id + " not found"));

        // Handle SensorType first
        SensorType type = sensorTypeRepository.findByName(dto.getType())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor type " + dto.getType() + " not found"));
        existingSensor.setType(type);

        // Handle Unit
        if (dto.getUnit() != null) {
            Unit unit = unitRepository.findByName(dto.getUnit())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit " + dto.getUnit() + " not found"));
            existingSensor.setUnit(unit);
        } else {
            existingSensor.setUnit(null);
        }

        // Now update only non-entity fields using the mapper
        sensorMapper.updateEntityFromDto(dto, existingSensor);

        return sensorMapper.toDto(sensorRepository.save(existingSensor));
    }

    public void deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sensor " + id + " not found");
        }
        sensorRepository.deleteById(id);
    }
}
