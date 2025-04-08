package com.sportclub.migracion_usuarios.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccesoRequest {
    @NotNull(message = "El DNI no puede ser nulo")
    private Long dni;
}
