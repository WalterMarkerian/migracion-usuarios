package com.sportclub.migracion_usuarios.user.application.update_by_id;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;

public interface UserUpdaterById {
    UserDTO updateUserById(Long id, UserDTO userDTO);
}