package com.sportclub.migracion_usuarios.sede.application.create;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeDuplicateNameException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedeCreatorImpl implements SedeCreator {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Override
    public SedeDTO createSede(SedeDTO sedeDTO)
            throws SedeDuplicateNameException {
        if (sedeRepository.existsByNombre(sedeDTO.getNombre())) {
            throw new SedeDuplicateNameException("La sede " + sedeDTO.getNombre() + " ya existe.");
        }

        Sede sede = sedeMapper.toEntity(sedeDTO);

        sede = sedeRepository.save(sede);

        return sedeMapper.toDto(sede);
    }

}
