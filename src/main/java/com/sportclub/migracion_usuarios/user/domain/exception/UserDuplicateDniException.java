package com.sportclub.migracion_usuarios.user.domain.exception;


import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class UserDuplicateDniException extends BaseException {
    private static final long serialVersionUID = 1487578649166312037L;
    public static final String DEFAULT_ERROR_CODE = "ERR_DUPLICATE_DNI";
    public static final String DEFAULT_ERROR_MESSAGE = "DNI duplicado";

    public UserDuplicateDniException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public UserDuplicateDniException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }

}