package com.example.monitorsensors.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.monitorsensors.model.Range;

public class RangeValidator implements ConstraintValidator<ValidRange, Range> {

    @Override
    public boolean isValid(Range range, ConstraintValidatorContext context) {
        if (range == null) {
            return true;
        }
        return range.getRangeFrom() < range.getRangeTo();
    }
}
