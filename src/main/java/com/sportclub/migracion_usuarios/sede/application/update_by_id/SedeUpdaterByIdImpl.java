package com.sportclub.migracion_usuarios.sede.application.update_by_id;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.source.SedeSourceRepository;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.target.SedeTargetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SedeUpdaterByIdImpl implements SedeUpdaterById {

    private final SedeSourceRepository sedeSourceRepository;
    private final SedeTargetRepository sedeTargetRepository;
    private final SedeMapper sedeMapper;

    @Transactional
    @Override
    public SedeDTO updateSedeById(Long id, SedeDTO sedeDTO) {
        log.debug("Actualización completa de sede ID: {}", id);

        Sede source = sedeSourceRepository.findById(id)
                .orElseThrow(() -> new SedeNotFoundException("Sede no encontrada con ID: " + id));

        // Reemplazar todos los campos, incluso si son null
        sedeMapper.replaceFromDto(sedeDTO, source);
        Sede updatedSource = sedeSourceRepository.save(source);

        sedeTargetRepository.findById(id).ifPresentOrElse(
                target -> {
                    sedeMapper.replaceFromDto(sedeDTO, target);
                    sedeTargetRepository.save(target);
                    log.info("Sede sincronizada en target - ID: {}", id);
                },
                () -> log.warn("Sede no encontrada en base de datos destino para sincronizar - ID: {}", id)
        );

        return sedeMapper.toDto(updatedSource);
    }
}
