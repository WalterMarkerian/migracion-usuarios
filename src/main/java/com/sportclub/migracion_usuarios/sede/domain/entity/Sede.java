package com.sportclub.migracion_usuarios.sede.domain.entity;

import com.sportclub.migracion_usuarios.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @Schema(description = "Nombre único de la sede",
            example = "Sede Central",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(nullable = false, length = 200)
    @Schema(description = "Dirección completa de la sede",
            example = "Calle 123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String direccion;

    @Column(nullable = false, length = 100)
    @Schema(description = "Ciudad donde se ubica la sede",
            example = "Buenos Aires",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String ciudad;

    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de usuarios asociados a esta sede")
    private Set<User> usuarios = new HashSet<>();

    // Métodos de conveniencia para la relación bidireccional
    public void addUsuario(User usuario) {
        usuarios.add(usuario);
        usuario.setSede(this);
    }

    public void removeUsuario(User usuario) {
        usuarios.remove(usuario);
        usuario.setSede(null);
    }
}