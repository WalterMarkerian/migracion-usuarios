package com.sportclub.migracion_usuarios.sede.application.create;


import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeDuplicateNameException;

public interface SedeCreator {

    SedeDTO createSede(SedeDTO sedeDTO)
            throws SedeDuplicateNameException;
}
