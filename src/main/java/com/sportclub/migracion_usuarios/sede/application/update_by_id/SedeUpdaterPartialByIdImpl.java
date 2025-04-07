package com.sportclub.migracion_usuarios.sede.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.source.SedeSourceRepository;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.target.SedeTargetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SedeUpdaterPartialByIdImpl implements SedeUpdaterPartialById {

    private final SedeSourceRepository sedeSourceRepository;
    private final SedeTargetRepository sedeTargetRepository;
    private final SedeMapper sedeMapper;

    @Transactional
    @Override
    public SedeDTO partialUpdateSedeById(Long id, SedeDTO sedeDTO) throws SedeNotFoundException {
        log.debug("Iniciando actualización parcial de sede ID: {}", id);

        Sede updatedSource = sedeSourceRepository.findById(id)
                .map(sede -> {
                    sedeMapper.updateFromDto(sedeDTO, sede);
                    return sedeSourceRepository.save(sede);
                })
                .orElseThrow(() -> {
                    log.warn("Sede no encontrada con ID: {}", id);
                    return new SedeNotFoundException("Sede no encontrada con ID: " + id);
                });

        sedeTargetRepository.findById(updatedSource.getId())
                .ifPresentOrElse(
                        target -> {
                            sedeMapper.updateFromDto(sedeDTO, target);
                            sedeTargetRepository.save(target);
                            log.info("Sede sincronizada en base de datos destino - Nombre: {}", target.getNombre());
                        },
                        () -> log.warn("No se encontró la sede en la base de datos destino: {}", updatedSource.getNombre())
                );

        log.info("Sede actualizada correctamente - ID: {}", id);
        return sedeMapper.toDto(updatedSource);
    }
}
