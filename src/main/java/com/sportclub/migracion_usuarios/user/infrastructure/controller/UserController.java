package com.sportclub.migracion_usuarios.user.infrastructure.controller;

import com.sportclub.migracion_usuarios.user.application.create.UserCreator;
import com.sportclub.migracion_usuarios.user.application.delete_by_id.UserDeleterById;
import com.sportclub.migracion_usuarios.user.application.find_all.UserFinderAll;
import com.sportclub.migracion_usuarios.user.application.find_by_id.UserFinderById;
import com.sportclub.migracion_usuarios.user.application.update_by_id.UserUpdaterPartialById;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDniCantBeNullException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDuplicateDniException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreator userCreator;
    private final UserFinderById userFinderById;
    private final UserFinderAll userFinderAll;
    private final UserDeleterById userDeleterById;
    private final UserUpdaterPartialById userUpdaterPartialById;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userCreator.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserDniCantBeNullException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserDuplicateDniException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userFinderById.findById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable Long dni) {
        try {
            UserDTO user = userFinderById.findById(dni);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        return ResponseEntity.ok(userFinderAll.findAll(page, size));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userUpdaterPartialById.partialUpdateUserById(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userDeleterById.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}