package com.sportclub.migracion_usuarios.user.domain.entity;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.user.domain.enums.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@Schema(description = "Entidad que representa a un usuario en el sistema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único autogenerado del usuario", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del usuario", example = "Juan", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(nullable = false, length = 50)
    @Schema(description = "Apellido del usuario", example = "Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @Column(nullable = false, unique = true, length = 100)
    @Schema(description = "Email del usuario (debe ser único)",
            example = "juanperez@mail.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Column(length = 20)
    @Schema(description = "Número de teléfono del usuario", example = "123456789")
    private String telefono;

    @Column(length = 20)
    @Schema(description = "Dni del usuario", example = "38618902")
    private Long dni;

    // Relación ManyToOne unidireccional con la Sede
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", nullable = false) // La columna de clave foránea en la tabla de usuarios
    @Schema(description = "Sede a la que pertenece el usuario")
    private Sede sede;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Schema(description = "Estado de autorización del usuario",
            allowableValues = {"AUTORIZADO", "DENEGADO"},
            example = "AUTORIZADO",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Estado estado;
}
