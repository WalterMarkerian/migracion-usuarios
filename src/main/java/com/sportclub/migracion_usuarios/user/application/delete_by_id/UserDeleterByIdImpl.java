package com.sportclub.migracion_usuarios.user.application.delete_by_id;

import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDeleterByIdImpl implements UserDeleterById {

    private static final String USER_NOT_FOUND_MSG = "Usuario con ID {} no encontrado";
    private static final String DELETION_SUCCESS_MSG = "Usuario con ID {} eliminado exitosamente";
    private static final String DELETION_ATTEMPT_MSG = "Intentando eliminar usuario con ID: {}";

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void deleteUserById(Long id) throws UserNotFoundException {
        log.debug(DELETION_ATTEMPT_MSG, id);

        if (!userRepository.existsById(id)) {
            log.warn(USER_NOT_FOUND_MSG, id);
            throw new UserNotFoundException("Usuario con ID " + id + " no encontrado");
        }

        userRepository.deleteById(id);
        log.info(DELETION_SUCCESS_MSG, id);
    }
}