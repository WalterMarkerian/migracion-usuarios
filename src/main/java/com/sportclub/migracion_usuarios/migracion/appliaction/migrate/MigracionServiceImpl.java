package com.sportclub.migracion_usuarios.migracion.appliaction.migrate;

import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.source.SedeSourceRepository;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.target.SedeTargetRepository;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.source.UserSourceRepository;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.target.UserTargetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigracionServiceImpl implements MigracionService {

    private final UserSourceRepository userSourceRepo;
    private final UserTargetRepository userTargetRepo;
    private final SedeSourceRepository sedeSourceRepo;
    private final SedeTargetRepository sedeTargetRepo;
    private final UserMapper userMapper;
    private final SedeMapper sedeMapper;

    @Override
    @Transactional
    public void migrarDatos() {
        log.info("===== INICIO MIGRACIÓN =====");

        migrarSedes();
        migrarUsuarios();

        log.info("===== MIGRACIÓN COMPLETADA =====");
    }

    private void migrarSedes() {
        List<Sede> sedesSource = sedeSourceRepo.findAll();

        for (Sede sourceSede : sedesSource) {
            Optional<Sede> existing = sedeTargetRepo.findByNombre(sourceSede.getNombre()); // ← Asumo que nombre es único
            if (existing.isEmpty()) {
                Sede nuevaSede = sedeMapper.toEntity(sedeMapper.toDto(sourceSede));
                sedeTargetRepo.save(nuevaSede);
                log.info("✔ Sede insertada: {}", nuevaSede.getNombre());
            } else {
                log.info("• Sede ya existente: {}", sourceSede.getNombre());
            }
        }
    }

    private void migrarUsuarios() {
        List<User> usersSource = userSourceRepo.findAll();

        for (User sourceUser : usersSource) {
            Optional<User> existingOpt = userTargetRepo.findByDni(sourceUser.getDni());

            Sede sedeTarget = sedeTargetRepo.findByNombre(sourceUser.getSede().getNombre())
                    .orElseThrow(() -> new RuntimeException("❌ Sede destino no encontrada: " + sourceUser.getSede().getNombre()));

            if (existingOpt.isEmpty()) {
                User nuevo = userMapper.toEntity(userMapper.toDto(sourceUser), sedeTarget);
                userTargetRepo.save(nuevo);
                log.info("✔ Usuario insertado: DNI {}", nuevo.getDni());
            } else {
                User existente = existingOpt.get();

                boolean datosActualizados =
                        !Objects.equals(sourceUser.getNombre(), existente.getNombre()) ||
                                !Objects.equals(sourceUser.getApellido(), existente.getApellido()) ||
                                !Objects.equals(sourceUser.getEmail(), existente.getEmail()) ||
                                !Objects.equals(sourceUser.getTelefono(), existente.getTelefono()) ||
                                !Objects.equals(sourceUser.getEstado(), existente.getEstado()) ||
                                !Objects.equals(sourceUser.getSede().getNombre(), existente.getSede().getNombre());

                if (datosActualizados) {
                    userMapper.updateFromDto(userMapper.toDto(sourceUser), existente, sedeTarget);
                    userTargetRepo.save(existente);
                    log.info("✏ Usuario actualizado: DNI {}", existente.getDni());
                } else {
                    log.info("• Usuario sin cambios: DNI {}", existente.getDni());
                }
            }
        }
    }
}
