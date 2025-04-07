package com.sportclub.migracion_usuarios.commons.validation;

import com.sportclub.migracion_usuarios.user.domain.enums.Estado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EstadoValidator implements ConstraintValidator<EstadoValido, Estado> {

    @Override
    public boolean isValid(Estado estado, ConstraintValidatorContext context) {
        if (estado == null) {
            return false;
        }

        return Arrays.stream(Estado.values())
                .anyMatch(valid -> valid.name().equalsIgnoreCase(estado.name()));
    }
}
