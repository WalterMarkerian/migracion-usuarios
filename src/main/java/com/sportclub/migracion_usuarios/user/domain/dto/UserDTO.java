package com.sportclub.migracion_usuarios.user.domain.dto;

import com.sportclub.migracion_usuarios.user.domain.enums.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {

    @Schema(description = "ID único del usuario", example = "0001")
    private Long id;

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

    @Schema(description = "ID de la sede a la que pertenece el usuario")
    private Long sedeId;

    @Schema(description = "Estado de autorización del usuario",
            allowableValues = {"AUTORIZADO", "DENEGADO"},
            example = "AUTORIZADO")
    private Estado estado;
}
