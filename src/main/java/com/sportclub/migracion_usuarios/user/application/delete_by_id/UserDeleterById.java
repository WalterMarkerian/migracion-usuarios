package com.sportclub.migracion_usuarios.user.application.delete_by_id;


import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDeleterById {
    void deleteUserById(Long id) throws UserNotFoundException;
}
