package com.sportclub.migracion_usuarios.security.service;

import com.sportclub.migracion_usuarios.security.dto.AccesoRequest;
import com.sportclub.migracion_usuarios.security.dto.AccesoResponse;
import com.sportclub.migracion_usuarios.security.exception.UserNotAuthorizedException;
import com.sportclub.migracion_usuarios.security.jwt.JwtService;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.target.UserTargetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccesoService {

    private final UserTargetRepository userRepo;
    private final JwtService jwtService;

    public AccesoResponse validarAcceso(AccesoRequest request) {
        log.info("Intento de acceso con DNI: {}", request.getDni());

        if (request.getDni() == null || request.getDni() <= 0) {
            log.warn("DNI ingresado incorrectamente: {}", request.getDni());
            throw new IllegalArgumentException("DNI ingresado incorrectamente");
        }

        User user = userRepo.findByDni(request.getDni())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con DNI: " + request.getDni()));


        switch (user.getEstado()) {
            case AUTORIZADO -> {
                String token = jwtService.generateToken(user.getDni().toString());
                log.info("Acceso autorizado para DNI {}", user.getDni());
                return new AccesoResponse("Acceso autorizado", token);
            }
            default -> throw new UserNotAuthorizedException();
        }
    }
}


