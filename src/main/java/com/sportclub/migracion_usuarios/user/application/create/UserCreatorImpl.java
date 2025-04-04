package com.sportclub.migracion_usuarios.user.application.create;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.domain.enums.Estado;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDniCantBeNullException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDuplicateDniException;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreatorImpl implements UserCreator {

    private static final String DNI_NULL_MESSAGE = "DNI no puede ser nulo";
    private static final String DNI_DUPLICATE_FORMAT = "DNI duplicado: %s";
    private static final String USER_CREATED_FORMAT = "Usuario creado exitosamente - ID: %d, DNI: %s";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) throws UserDuplicateDniException, UserDniCantBeNullException {
        validateUserDTO(userDTO);
        checkDuplicateDni(userDTO.getDni());

        User user = createAndSaveUser(userDTO);

        logCreationSuccess(user);
        return userMapper.toDto(user);
    }

    private void validateUserDTO(UserDTO userDTO) throws UserDniCantBeNullException {
        if (userDTO.getDni() == null) {
            log.error("Intento de creación de usuario sin DNI");
            throw new UserDniCantBeNullException(DNI_NULL_MESSAGE);
        }
    }

    private void checkDuplicateDni(Long dni) throws UserDuplicateDniException {
        log.debug("Verificando existencia de usuario con DNI: {}", dni);
        if (userRepository.existsByDni(dni)) {
            String errorMsg = String.format(DNI_DUPLICATE_FORMAT, dni);
            log.warn(errorMsg);
            throw new UserDuplicateDniException(errorMsg);
        }
    }

    private User createAndSaveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        setDefaultStatusIfNull(user);
        return userRepository.save(user);
    }

    private void setDefaultStatusIfNull(User user) {
        if (user.getEstado() == null) {
            log.debug("Asignando estado por defecto al usuario");
            user.setEstado(Estado.DENEGADO);
        }
    }

    private void logCreationSuccess(User user) {
        log.info(String.format(USER_CREATED_FORMAT, user.getId(), user.getDni()));
    }
}