package com.sportclub.migracion_usuarios.user.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdaterPartialByIdImpl implements UserUpdaterPartialById {

    private static final String USER_NOT_FOUND = "Usuario no encontrado con ID: {}";
    private static final String UPDATE_START = "Iniciando actualización parcial para usuario ID: {}";
    private static final String UPDATE_SUCCESS = "Usuario actualizado exitosamente - ID: {}";

    private final UserRepository userRepository;
    private final SedeRepository sedeRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO partialUpdateUserById(Long id, UserDTO userDTO) throws UserNotFoundException {
        log.debug(UPDATE_START, id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(USER_NOT_FOUND, id);
                    return new UserNotFoundException("Usuario no encontrado con id: " + id);
                });
        Sede sede = sedeRepository.findById(userDTO.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + userDTO.getSedeId()));


        userMapper.updateFromDto(userDTO, user, sede);
        User updatedUser = userRepository.save(user);

        log.info(UPDATE_SUCCESS, id);
        return userMapper.toDto(updatedUser);
    }
}