package com.sportclub.migracion_usuarios.user.domain.exception;


import com.sportclub.migracion_usuarios.commons.domain.BaseException;

public class UserNotFoundException extends BaseException {
    private static final long serialVersionUID = 8237342515687888090L;
    public static final String DEFAULT_ERROR_CODE = "ERR_USER_NOT_FOUND";
    public static final String DEFAULT_ERROR_MESSAGE = "Usuario no encontrado";

    public UserNotFoundException() {
        super(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);  // Por defecto el mensaje está hardcodeado
    }

    public UserNotFoundException(String customMessage) {
        super(DEFAULT_ERROR_CODE, customMessage);  // Puedes pasar un mensaje personalizado
    }
}