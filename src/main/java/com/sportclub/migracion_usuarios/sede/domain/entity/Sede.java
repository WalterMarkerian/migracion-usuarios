package com.sportclub.migracion_usuarios.sede.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sedes")
@Schema(description = "Entidad que representa una sede física de la organización")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único autogenerado de la sede", example = "1")
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    @Schema(description = "Nombre único de la sede", example = "Sede Central", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(nullable = false, length = 200)
    @Schema(description = "Dirección completa de la sede", example = "Calle 123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String direccion;

    @Column(nullable = false, length = 100)
    @Schema(description = "Ciudad donde se ubica la sede", example = "Buenos Aires", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ciudad;
}
