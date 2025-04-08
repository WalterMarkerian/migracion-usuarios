package com.sportclub.migracion_usuarios.migracion.config;


import com.sportclub.migracion_usuarios.migracion.appliaction.migrate.MigracionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigracionStartupRunner {

    private final MigracionService migracionService;

    @EventListener(ApplicationReadyEvent.class)
    public void ejecutarMigracionAlInicio() {
        log.info("Iniciando migración automática al levantar la app...");
        try {
            migracionService.migrarDatos();
        } catch (Exception e) {
            log.error("Error al ejecutar migración automática: {}", e.getMessage(), e);
        }
    }
}
