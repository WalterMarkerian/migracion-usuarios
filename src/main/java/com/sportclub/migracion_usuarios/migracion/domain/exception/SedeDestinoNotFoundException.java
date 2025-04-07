package com.sportclub.migracion_usuarios.migracion.domain.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class SedeDestinoNotFoundException extends BaseException {
    private static final long serialVersionUID = 1487578649166312037L;
    public static final String DEFAULT_ERROR_CODE = "ERR_SEDE_DESTINO_NOT_FOUND";
    public static final String DEFAULT_ERROR_MESSAGE = "Sede destino no encontrada";

    public SedeDestinoNotFoundException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public SedeDestinoNotFoundException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }
}
