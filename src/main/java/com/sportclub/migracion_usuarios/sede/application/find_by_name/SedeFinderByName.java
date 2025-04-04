package com.sportclub.migracion_usuarios.sede.application.find_by_name;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;

public interface SedeFinderByName {

    SedeDTO findByName(String nombre) throws SedeNotFoundException;
}
