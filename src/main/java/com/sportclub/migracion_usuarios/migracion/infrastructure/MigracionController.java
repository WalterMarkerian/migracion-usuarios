package com.sportclub.migracion_usuarios.migracion.infrastructure;

import com.sportclub.migracion_usuarios.migracion.appliaction.migrate.MigracionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migracion")
@RequiredArgsConstructor
public class MigracionController {

    private final MigracionService migracionService;

    @Operation(
            summary = "Ejecutar migración de datos",
            description = "Migra datos de usuarios y sedes desde la base de datos fuente hacia la base de datos destino."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Migración completada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno durante la migración")
    })
    @PostMapping
    public ResponseEntity<String> migrar() {
        try {
            migracionService.migrarDatos();
            return ResponseEntity.ok("Migración completada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error durante la migración: " + e.getMessage());
        }
    }
}
