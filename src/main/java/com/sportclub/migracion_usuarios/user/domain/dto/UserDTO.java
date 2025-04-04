package com.sportclub.migracion_usuarios.user.domain.dto;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
    @Schema(description = "ID único del usuario", example = "0001")
    private String id;

    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @Schema(description = "Email del usuario", example = "juanperez@mail.com")
    private String email;

    @Schema(description = "Teléfono del usuario", example = "123456789")
    private String telefono;

    @Schema(description = "DNI del usuario", example = "38618902")
    private Long dni;

    @Schema(description = "Objeto que contiene los datos de la sede")
    private SedeDTO sede;

    @Schema(description = "Estado de autorización del usuario",
            allowableValues = {"autorizado", "no autorizado"},
            example = "autorizado")
    private String estado;
}