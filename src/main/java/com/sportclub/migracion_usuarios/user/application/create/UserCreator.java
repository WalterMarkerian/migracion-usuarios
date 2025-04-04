package com.sportclub.migracion_usuarios.user.application.create;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDniCantBeNullException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDuplicateDniException;
import org.springframework.stereotype.Service;

@Service
public interface UserCreator {
    UserDTO createUser(UserDTO userDTO) throws UserDuplicateDniException, UserDniCantBeNullException;

}
