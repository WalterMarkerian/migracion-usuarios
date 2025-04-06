package com.sportclub.migracion_usuarios.sede.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa una sede en el sistema")
public class SedeDTO {

    @Schema(
            description = "Identificador único de la sede (automático, no requerido para creación)",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY,  // Solo lectura en Swagger
            requiredMode = Schema.RequiredMode.NOT_REQUIRED  // No es requerido
    )
    private Long id;

    @Schema(description = "Nombre de la sede", example = "Sede Central", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Dirección física de la sede", example = "Calle 123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String direccion;

    @Schema(description = "Ciudad donde se encuentra la sede", example = "Buenos Aires", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ciudad;
}