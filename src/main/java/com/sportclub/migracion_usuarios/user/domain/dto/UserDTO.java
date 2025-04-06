package com.sportclub.migracion_usuarios.user.domain.dto;

import com.sportclub.migracion_usuarios.user.domain.enums.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {

    @Schema(
            description = "ID único del usuario (generado automáticamente)",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long id;

    @Schema(description = "Nombre del usuario", example = "Juan", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @Schema(description = "Email del usuario", example = "juanperez@mail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Teléfono del usuario", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telefono;

    @Schema(description = "DNI del usuario", example = "38618902", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dni;

    @Schema(description = "ID de la sede a la que pertenece el usuario", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long sedeId;

    @Schema(
            description = "Estado de autorización del usuario",
            allowableValues = {"AUTORIZADO", "DENEGADO"},
            example = "DENEGADO"
    )
    private Estado estado;
}