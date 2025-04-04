package com.sportclub.migracion_usuarios.user.application.update_by_id;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;

public interface UserUpdaterPartialById {
    UserDTO partialUpdateUserById(Long id, UserDTO userDTO) throws UserNotFoundException;
}