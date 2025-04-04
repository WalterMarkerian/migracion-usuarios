package com.sportclub.migracion_usuarios.user.domain.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class UserDniCantBeNullException extends BaseException {
    private static final long serialVersionUID = 1487578649166312037L;
    public static final String DEFAULT_ERROR_CODE = "ERR_DNI_NULL";
    public static final String DEFAULT_ERROR_MESSAGE = "DNI no puede ser nulo";

    public UserDniCantBeNullException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public UserDniCantBeNullException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }
}