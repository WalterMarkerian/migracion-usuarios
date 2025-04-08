package com.sportclub.migracion_usuarios.security.exception;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class UserNotAuthorizedException extends BaseException {
    private static final long serialVersionUID = 8237342515687888090L;
    public static final String DEFAULT_ERROR_CODE = "ERR_USER_NOT_AUTHORIZED";
    public static final String DEFAULT_ERROR_MESSAGE = "Usuario no autorizado";

    public UserNotAuthorizedException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public UserNotAuthorizedException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);
    }
}