package com.sportclub.migracion_usuarios.user.application.find_by_id;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFinderByIdImpl implements UserFinderById {

    private static final String SEARCH_START = "Buscando usuario con ID: {}";
    private static final String USER_NOT_FOUND = "Usuario no encontrado con ID: {}";
    private static final String USER_FOUND = "Usuario encontrado con ID: {}";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO findById(Long id) throws UserNotFoundException {
        log.debug(SEARCH_START, id);

        return userRepository.findById(id)
                .map(user -> {
                    log.debug(USER_FOUND, id);
                    return userMapper.toDto(user);
                })
                .orElseThrow(() -> {
                    log.warn(USER_NOT_FOUND, id);
                    return new UserNotFoundException("Usuario no encontrado con ID: " + id);
                });
    }
}