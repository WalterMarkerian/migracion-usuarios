package com.sportclub.migracion_usuarios.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EstadoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EstadoValido {
    String message() default "El estado debe ser AUTORIZADO o DENEGADO";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
