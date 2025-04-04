package com.sportclub.migracion_usuarios.user.application.find_by_id;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserFinderById {
    UserDTO findById(Long id) throws UserNotFoundException;
}