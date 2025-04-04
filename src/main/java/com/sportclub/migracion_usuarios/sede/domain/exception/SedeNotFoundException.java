package com.sportclub.migracion_usuarios.sede.domain.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class SedeNotFoundException extends BaseException {
    private static final long serialVersionUID = 8237342515687888090L;
    public static final String DEFAULT_ERROR_CODE = "ERR_SEDE_NOT_FOUND";
    public static final String DEFAULT_ERROR_MESSAGE = "Sede no encontrada";

    public SedeNotFoundException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);  // Por defecto el mensaje está hardcodeado
    }

    public SedeNotFoundException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);  // Puedes pasar un mensaje personalizado
    }
}