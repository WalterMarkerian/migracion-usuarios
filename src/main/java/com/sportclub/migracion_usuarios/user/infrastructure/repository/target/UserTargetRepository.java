package com.sportclub.migracion_usuarios.user.infrastructure.repository.target;

import com.sportclub.migracion_usuarios.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTargetRepository extends JpaRepository<User, Long> {
    Optional<User> findByDni(Long dni);
}
