package com.sportclub.migracion_usuarios.commons.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sportclub.migracion_usuarios.user.domain.enums.Estado;

import java.io.IOException;
import java.util.Arrays;

public class EstadoDeserializer extends JsonDeserializer<Estado> {

    @Override
    public Estado deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText().trim();

        return Arrays.stream(Estado.values())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Estado inválido: " + value + ". Solo se permite AUTORIZADO o DENEGADO."));
    }
}
