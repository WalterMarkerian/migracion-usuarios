package com.sportclub.migracion_usuarios.sede.infrastructure.controller;

import com.sportclub.migracion_usuarios.sede.application.create.SedeCreator;
import com.sportclub.migracion_usuarios.sede.application.delete_by_id.SedeDeleterById;
import com.sportclub.migracion_usuarios.sede.application.find_all.SedeFinderAll;
import com.sportclub.migracion_usuarios.sede.application.find_by_id.SedeFinderById;
import com.sportclub.migracion_usuarios.sede.application.find_by_nombre.SedeFinderByNombre;
import com.sportclub.migracion_usuarios.sede.application.update_by_id.SedeUpdaterPartialById;
import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sedes")
@RequiredArgsConstructor
public class SedeController {

    private final SedeCreator sedeCreator;
    private final SedeFinderById sedeFinderById;
    private final SedeFinderByNombre sedeFinderByNombre;
    private final SedeFinderAll sedeFinderAll;
    private final SedeDeleterById sedeDeleterById;
    private final SedeUpdaterPartialById sedeUpdaterPartialById;

    @PostMapping
    public ResponseEntity<SedeDTO> createSede(@RequestBody SedeDTO sedeDTO) {
        SedeDTO createdSede = sedeCreator.createSede(sedeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSede);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> getSedeById(@PathVariable Long id) {
        SedeDTO sede = sedeFinderById.findById(id);
        return ResponseEntity.ok(sede);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<SedeDTO> getSedeByNombre(@PathVariable String nombre) {
        SedeDTO sede = sedeFinderByNombre.findByNombre(nombre);
        return ResponseEntity.ok(sede);
    }

    @GetMapping
    public ResponseEntity<?> getAllSedes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sort) {
        return ResponseEntity.ok(sedeFinderAll.findAll(page, pageSize, sort));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SedeDTO> partialUpdateSede(
            @PathVariable Long id,
            @RequestBody SedeDTO sedeDTO) {
        SedeDTO updatedSede = sedeUpdaterPartialById.partialUpdateSedeById(id, sedeDTO);
        return ResponseEntity.ok(updatedSede);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        sedeDeleterById.deleteSedeById(id);
        return ResponseEntity.noContent().build();
    }
}
