package com.sportclub.migracion_usuarios.security.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class DniInvalidFormatException extends BaseException {
    private static final long serialVersionUID = 8237342515687888090L;
    public static final String DEFAULT_ERROR_CODE = "DNI_INVALID_FORMAT";
    public static final String DEFAULT_ERROR_MESSAGE = "El formato del DNI es invalido";

    public DniInvalidFormatException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public DniInvalidFormatException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }
}