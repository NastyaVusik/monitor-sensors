package com.example.monitorsensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.monitorsensors.dto.SensorRequestDTO;
import com.example.monitorsensors.dto.SensorResponseDTO;
import com.example.monitorsensors.model.Sensor;
import com.example.monitorsensors.model.SensorType;
import com.example.monitorsensors.model.Unit;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "stringToSensorType")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "stringToUnit")
    @Mapping(target = "id", ignore = true)
    Sensor toEntity(SensorRequestDTO dto);

    @Mapping(source = "type", target = "type", qualifiedByName = "sensorTypeToString")
    @Mapping(source = "unit", target = "unit", qualifiedByName = "unitToString")
    SensorResponseDTO toDto(Sensor sensor);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "unit", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(SensorRequestDTO dto, @MappingTarget Sensor sensor);

    @Named("stringToSensorType")
    default SensorType stringToSensorType(String name) {
        if (name == null) {
            return null;
        }
        SensorType sensorType = new SensorType();
        sensorType.setName(name);
        return sensorType;
    }

    @Named("sensorTypeToString")
    default String sensorTypeToString(SensorType sensorType) {
        return sensorType != null ? sensorType.getName() : null;
    }

    @Named("stringToUnit")
    default Unit stringToUnit(String name) {
        if (name == null) {
            return null;
        }
        Unit unit = new Unit();
        unit.setName(name);
        return unit;
    }

    @Named("unitToString")
    default String unitToString(Unit unit) {
        return unit != null ? unit.getName() : null;
    }
}
