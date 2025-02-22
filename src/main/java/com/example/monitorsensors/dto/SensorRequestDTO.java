package com.example.monitorsensors.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorRequestDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String model;

    @Valid
    private RangeDTO range;

    @NotBlank
    private String type;

    private String unit;

    @Size(max = 40)
    private String location;

    @Size(max = 200)
    private String description;
}
