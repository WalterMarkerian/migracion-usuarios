package com.sportclub.migracion_usuarios.user.infrastructure.repository;

import com.sportclub.migracion_usuarios.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByDni(Long dni);

    Optional<User> findByDni(Long dni);
}