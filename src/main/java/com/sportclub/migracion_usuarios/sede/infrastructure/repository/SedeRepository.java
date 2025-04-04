package com.sportclub.migracion_usuarios.sede.infrastructure.repository;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    Optional<Sede> findByNombre(String nombre);

    boolean existsByNombre(String nombre); // Keep this if you need it for other purposes
}
