package com.sportclub.migracion_usuarios.user.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.source.SedeSourceRepository;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.target.SedeTargetRepository;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.source.UserSourceRepository;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.target.UserTargetRepository;
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
    private static final String USER_UPDATED_SOURCE = "Usuario actualizado en base source - DNI: {}";
    private static final String USER_SYNCED_TARGET = "Usuario sincronizado en base de datos destino - DNI: {}";
    private static final String USER_NOT_FOUND_TARGET = "No se encontró el usuario en la base de datos destino - DNI: {}";
    private static final String SEDE_NOT_FOUND_SOURCE = "Sede no encontrada con ID: ";
    private static final String SEDE_NOT_FOUND_TARGET = "Sede destino no encontrada: ";

    private final UserSourceRepository userSourceRepository;
    private final UserTargetRepository userTargetRepository;
    private final SedeSourceRepository sedeSourceRepository;
    private final SedeTargetRepository sedeTargetRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO partialUpdateUserById(Long id, UserDTO userDTO) throws UserNotFoundException {
        log.debug(UPDATE_START, id);

        User user = userSourceRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(USER_NOT_FOUND, id);
                    return new UserNotFoundException("Usuario no encontrado con id: " + id);
                });

        Sede sedeSource = sedeSourceRepository.findById(userDTO.getSedeId())
                .orElseThrow(() -> new RuntimeException(SEDE_NOT_FOUND_SOURCE + userDTO.getSedeId()));

        userMapper.updateFromDto(userDTO, user, sedeSource);
        User updatedSourceUser = userSourceRepository.save(user);
        log.info(USER_UPDATED_SOURCE, updatedSourceUser.getDni());

        userTargetRepository.findByDni(updatedSourceUser.getDni()).ifPresentOrElse(
                targetUser -> {
                    Sede sedeTarget = sedeTargetRepository.findByNombre(sedeSource.getNombre())
                            .orElseThrow(() -> new RuntimeException(SEDE_NOT_FOUND_TARGET + sedeSource.getNombre()));
                    userMapper.updateFromDto(userDTO, targetUser, sedeTarget);
                    userTargetRepository.save(targetUser);
                    log.info(USER_SYNCED_TARGET, targetUser.getDni());
                },
                () -> log.warn(USER_NOT_FOUND_TARGET, updatedSourceUser.getDni())
        );

        log.info(UPDATE_SUCCESS, id);
        return userMapper.toDto(updatedSourceUser);
    }
}
