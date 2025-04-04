package com.sportclub.migracion_usuarios.commons.create_message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ErrorMessageCreatorImpl implements ErrorMessageCreator {
    @Autowired
    MessageSource messageResource;

    public String getMessage(String code) {
        // Usando Locale.of() (Java 19+)
        return messageResource.getMessage(code, null, Locale.of("en", "US"));

        // Otra alternativa válida:
        // return messageResource.getMessage(code, null, Locale.US);
    }
}