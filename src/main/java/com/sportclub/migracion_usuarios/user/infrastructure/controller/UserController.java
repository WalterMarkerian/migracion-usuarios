package com.sportclub.migracion_usuarios.user.infrastructure.controller;

import com.sportclub.migracion_usuarios.user.application.create.UserCreator;
import com.sportclub.migracion_usuarios.user.application.delete_by_id.UserDeleterById;
import com.sportclub.migracion_usuarios.user.application.find_all.UserFinderAll;
import com.sportclub.migracion_usuarios.user.application.find_by_id.UserFinderById;
import com.sportclub.migracion_usuarios.user.application.update_by_id.UserUpdaterPartialById;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserCreator userCreator;
    private final UserFinderById userFinderById;
    private final UserFinderAll userFinderAll;
    private final UserDeleterById userDeleterById;
    private final UserUpdaterPartialById userUpdaterPartialById;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userCreator.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userFinderById.findById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<UserDTO> getUserByDni(@PathVariable Long dni) {
        return ResponseEntity.ok(userFinderById.findById(dni));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sort) {
        return ResponseEntity.ok(userFinderAll.findAll(page, pageSize, sort));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userUpdaterPartialById.partialUpdateUserById(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userDeleterById.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
