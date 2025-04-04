package com.sportclub.migracion_usuarios.user.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Estados básicos de autorización de un usuario",
        enumAsRef = true
)
public enum Estado {

    @Schema(
            description = "El usuario tiene acceso autorizado al sistema",
            example = "AUTORIZADO"
    )
    AUTORIZADO,

    @Schema(
            description = "El acceso del usuario ha sido denegado",
            example = "DENEGADO"
    )
    DENEGADO;

    // Método para verificar si el estado es autorizado
    public boolean esAutorizado() {
        return this == AUTORIZADO;
    }

    // Método para obtener descripción legible
    public String getDescripcion() {
        return this == AUTORIZADO ? "Acceso autorizado" : "Acceso denegado";
    }
}