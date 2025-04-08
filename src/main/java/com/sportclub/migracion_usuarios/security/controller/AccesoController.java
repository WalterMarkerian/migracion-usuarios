package com.sportclub.migracion_usuarios.security.controller;

import com.sportclub.migracion_usuarios.security.dto.AccesoRequest;
import com.sportclub.migracion_usuarios.security.dto.AccesoResponse;
import com.sportclub.migracion_usuarios.security.service.AccesoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acceso")
@RequiredArgsConstructor
public class AccesoController {

    private final AccesoService accesoService;

    @PostMapping
    public ResponseEntity<AccesoResponse> acceder(@RequestBody @Valid AccesoRequest request) {
        AccesoResponse response = accesoService.validarAcceso(request);
        return ResponseEntity.ok(response);
    }
}
