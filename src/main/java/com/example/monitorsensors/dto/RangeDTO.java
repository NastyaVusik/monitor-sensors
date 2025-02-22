package com.example.monitorsensors.dto;

import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RangeDTO {

    @Positive
    private Integer rangeFrom;

    @Positive
    private Integer rangeTo;
}
