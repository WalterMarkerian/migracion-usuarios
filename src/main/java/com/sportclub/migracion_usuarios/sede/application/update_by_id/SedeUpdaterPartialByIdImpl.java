package com.sportclub.migracion_usuarios.sede.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SedeUpdaterPartialByIdImpl implements SedeUpdaterPartialById {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Transactional
    @Override
    public SedeDTO partialUpdateSedeById(Long id, SedeDTO sedeDTO) throws SedeNotFoundException {
        return sedeRepository.findById(id)
                .map(sede -> {
                    sedeMapper.updateFromDto(sedeDTO, sede);
                    return sedeMapper.toDto(sedeRepository.save(sede));
                })
                .orElseThrow(() -> new SedeNotFoundException("Sede no encontrada con ID: " + id));
    }
}