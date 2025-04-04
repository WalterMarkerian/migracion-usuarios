package com.sportclub.migracion_usuarios.sede.domain.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class SedeDuplicateNameException extends BaseException {
    private static final long serialVersionUID = 1487578649166312037L;
    public static final String DEFAULT_ERROR_CODE = "ERR_DUPLICATE_SEDENAME";
    public static final String DEFAULT_ERROR_MESSAGE = "Nombre de la sede duplicado";

    public SedeDuplicateNameException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public SedeDuplicateNameException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }

}