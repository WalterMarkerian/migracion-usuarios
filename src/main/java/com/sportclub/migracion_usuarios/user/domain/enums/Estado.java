package com.sportclub.migracion_usuarios.user.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sportclub.migracion_usuarios.commons.deserializer.EstadoDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Estados básicos de autorización de un usuario",
        enumAsRef = true
)
@JsonDeserialize(using = EstadoDeserializer.class)
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


}