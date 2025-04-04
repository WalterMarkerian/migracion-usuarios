package com.sportclub.migracion_usuarios.sede.infrastructure.controller;

import com.sportclub.migracion_usuarios.sede.application.create.SedeCreator;
import com.sportclub.migracion_usuarios.sede.application.delete_by_id.SedeDeleterById;
import com.sportclub.migracion_usuarios.sede.application.find_all.SedeFinderAll;
import com.sportclub.migracion_usuarios.sede.application.find_by_id.SedeFinderById;
import com.sportclub.migracion_usuarios.sede.application.find_by_name.SedeFinderByName;
import com.sportclub.migracion_usuarios.sede.application.update_by_id.SedeUpdaterPartialById;
import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeDuplicateNameException;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sedes")
@RequiredArgsConstructor
public class SedeController {

    private final SedeCreator sedeCreator;
    private final SedeFinderById sedeFinderById;
    private final SedeFinderByName sedeFinderByName;
    private final SedeFinderAll sedeFinderAll;
    private final SedeDeleterById sedeDeleterById;
    private final SedeUpdaterPartialById sedeUpdaterPartialById;

    @PostMapping
    public ResponseEntity<?> createSede(@RequestBody SedeDTO sedeDTO) {
        try {
            SedeDTO createdSede = sedeCreator.createSede(sedeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSede);
        } catch (SedeDuplicateNameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSedeById(@PathVariable Long id) {
        try {
            SedeDTO sede = sedeFinderById.findById(id);
            return ResponseEntity.ok(sede);
        } catch (SedeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getSedeByName(@PathVariable String name) {
        try {
            SedeDTO sede = sedeFinderByName.findByName(name);
            return ResponseEntity.ok(sede);
        } catch (SedeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSedes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(sedeFinderAll.findAll(page, size));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateSede(
            @PathVariable Long id,
            @RequestBody SedeDTO sedeDTO) {
        try {
            SedeDTO updatedSede = sedeUpdaterPartialById.partialUpdateSedeById(id, sedeDTO);
            return ResponseEntity.ok(updatedSede);
        } catch (SedeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSede(@PathVariable Long id) {
        try {
            sedeDeleterById.deleteSedeById(id);
            return ResponseEntity.noContent().build();
        } catch (SedeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}