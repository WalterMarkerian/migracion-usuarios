package com.sportclub.migracion_usuarios.sede.application.delete_by_id;


import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;

public interface SedeDeleterById {
    void deleteSedeById(Long id) throws SedeNotFoundException;
}
