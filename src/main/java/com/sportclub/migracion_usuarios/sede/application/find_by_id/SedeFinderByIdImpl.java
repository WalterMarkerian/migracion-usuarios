package com.sportclub.migracion_usuarios.sede.application.find_by_id;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedeFinderByIdImpl implements SedeFinderById {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Override
    public SedeDTO findById(Long id) throws SedeNotFoundException {
        return sedeRepository.findById(id)
                .map(sedeMapper::toDto)
                .orElseThrow(() -> new SedeNotFoundException("Sede con ID " + id + " no encontrada"));
    }
}