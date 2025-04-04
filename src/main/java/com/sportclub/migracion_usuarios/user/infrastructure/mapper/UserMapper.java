package com.sportclub.migracion_usuarios.user.infrastructure.mapper;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;

public class UserMapper {

    // Método para convertir de User a UserDTO
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellido(user.getApellido());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefono(user.getTelefono());
        userDTO.setDni(user.getDni());
        userDTO.setSedeId(user.getSede() != null ? user.getSede().getId() : null);
        userDTO.setEstado(user.getEstado());

        return userDTO;
    }

    // Método para convertir de UserDTO a User
    public User toEntity(UserDTO userDTO, Sede sede) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellido());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        user.setDni(userDTO.getDni());
        user.setSede(sede);
        user.setEstado(userDTO.getEstado());

        return user;
    }

    // Método para actualizar la entidad User usando los datos del DTO
    public void updateFromDto(UserDTO userDTO, User user, Sede sede) {
        if (userDTO == null || user == null) {
            return;
        }

        // Actualizar los campos de la entidad User usando los valores del DTO
        if (userDTO.getNombre() != null) {
            user.setNombre(userDTO.getNombre());
        }
        if (userDTO.getApellido() != null) {
            user.setApellido(userDTO.getApellido());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefono() != null) {
            user.setTelefono(userDTO.getTelefono());
        }
        if (userDTO.getDni() != null) {
            user.setDni(userDTO.getDni());
        }
        if (userDTO.getEstado() != null) {
            user.setEstado(userDTO.getEstado());
        }

        // Solo actualiza la sede si el id es válido
        if (userDTO.getSedeId() != null && sede != null && sede.getId().equals(userDTO.getSedeId())) {
            user.setSede(sede);
        }
    }
}
