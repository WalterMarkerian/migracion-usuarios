package com.sportclub.migracion_usuarios.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccesoResponse {
    private String mensaje;
    private String token;
}
