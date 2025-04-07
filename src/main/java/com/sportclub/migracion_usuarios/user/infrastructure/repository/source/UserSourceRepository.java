package com.sportclub.migracion_usuarios.user.infrastructure.repository.source;

import com.sportclub.migracion_usuarios.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSourceRepository extends JpaRepository<User, Long> {
    Optional<User> findByDni(Long dni);

    boolean existsByDni(Long dni);
}
