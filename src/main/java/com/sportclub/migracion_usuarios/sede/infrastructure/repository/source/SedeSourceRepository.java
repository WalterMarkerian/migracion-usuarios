package com.sportclub.migracion_usuarios.sede.infrastructure.repository.source;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SedeSourceRepository extends JpaRepository<Sede, Long> {
    Optional<Sede> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
