package com.sportclub.migracion_usuarios.sede.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;

public interface SedeUpdaterById {
    SedeDTO updateSedeById(Long id, SedeDTO sedeDTO);
}
