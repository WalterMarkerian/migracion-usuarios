package com.sportclub.migracion_usuarios.sede.application.find_all;

import com.sportclub.migracion_usuarios.commons.dto.PageResponseDTO;
import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;

public interface SedeFinderAll {
    PageResponseDTO<SedeDTO> findAll(Integer page, Integer pageSize, String sort);
}
