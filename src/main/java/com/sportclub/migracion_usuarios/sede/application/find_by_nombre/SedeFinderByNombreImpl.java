package com.sportclub.migracion_usuarios.sede.application.find_by_nombre;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedeFinderByNombreImpl implements SedeFinderByNombre {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Override
    public SedeDTO findByNombre(String nombre) throws SedeNotFoundException {
        return sedeRepository.findByNombre(nombre)
                .map(sedeMapper::toDto)
                .orElseThrow(() -> new SedeNotFoundException("Sede con nombre '" + nombre + "' no encontrada"));
    }
}