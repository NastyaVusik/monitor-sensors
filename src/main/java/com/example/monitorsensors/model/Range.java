package com.example.monitorsensors.model;

import com.example.monitorsensors.validation.ValidRange;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidRange
public class Range {

    @Positive
    private int rangeFrom;

    @Positive
    private int rangeTo;
}
