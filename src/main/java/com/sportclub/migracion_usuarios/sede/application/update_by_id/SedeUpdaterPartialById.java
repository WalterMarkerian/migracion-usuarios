package com.sportclub.migracion_usuarios.sede.application.update_by_id;


import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;

public interface SedeUpdaterPartialById {
    SedeDTO partialUpdateSedeById(Long id, SedeDTO sedeDTO) throws SedeNotFoundException;
}