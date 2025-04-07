package com.sportclub.migracion_usuarios.sede.application.delete_by_id;

import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.source.SedeSourceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedeDeleterByIdImpl implements SedeDeleterById {
    private final SedeSourceRepository sedeRepository;


    @Override
    @Transactional
    public void deleteSedeById(Long id) throws SedeNotFoundException {
        if (!sedeRepository.existsById(id)) {
            throw new SedeNotFoundException("Sede con ID " + id + " no encontrada");
        }
        sedeRepository.deleteById(id);
    }
}
