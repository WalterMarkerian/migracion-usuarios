package com.sportclub.migracion_usuarios.user.application.find_all;

import com.sportclub.migracion_usuarios.commons.dto.PageResponseDTO;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserFinderAll {
    PageResponseDTO<UserDTO> findAll(Integer page, Integer pageSize, String sort);
}