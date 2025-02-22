package com.example.monitorsensors.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorResponseDTO {

    private Long id;
    private String name;
    private String model;
    private RangeDTO range;
    private String type;
    private String unit;
    private String location;
    private String description;
}
