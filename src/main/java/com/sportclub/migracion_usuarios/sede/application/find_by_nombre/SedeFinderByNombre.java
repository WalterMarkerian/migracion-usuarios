package com.sportclub.migracion_usuarios.sede.application.find_by_nombre;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;

public interface SedeFinderByNombre {

    SedeDTO findByNombre(String nombre) throws SedeNotFoundException;
}
