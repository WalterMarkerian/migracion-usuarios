package com.sportclub.migracion_usuarios.commons.handler;

import com.sportclub.migracion_usuarios.commons.domain.BaseException;
import com.sportclub.migracion_usuarios.commons.model.ApiError;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeDuplicateNameException;
import com.sportclub.migracion_usuarios.sede.domain.exception.SedeNotFoundException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDniCantBeNullException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserDuplicateDniException;
import com.sportclub.migracion_usuarios.user.domain.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionHandler {
    private final MessageSource messageSource;


    private ApiError buildGeneralResponse(BaseException e, HttpServletRequest request) {
        String exceptionName = e.getClass().getSimpleName();
        String completePath = request.getMethod().concat(":").concat(request.getRequestURI());
        String errorMessage = messageSource.getMessage(e.getCode() + "_MESSAGE", null, e.getMessage(), LocaleContextHolder.getLocale());
        String errorCode = messageSource.getMessage(e.getCode() + "_CODE", null, e.getCode(), LocaleContextHolder.getLocale());
        log.error("Unable to run the requested operation: " + completePath, e);

        return ApiError.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .error(exceptionName)
                .code(errorCode)
                .message(errorMessage)
                .detailedMessage(e.getMessage())
                .path(completePath)
                .build();
    }

    @ExceptionHandler({UserDuplicateDniException.class, SedeDuplicateNameException.class
            , UserDniCantBeNullException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(BaseException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildGeneralResponse(e, request));
    }

    @ExceptionHandler({UserNotFoundException.class, SedeNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(BaseException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildGeneralResponse(e, request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, HttpServletRequest request) {
        if (e instanceof BaseException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(buildGeneralResponse((BaseException) e, request));
        }

        log.error("Unhandled exception occurred: ", e);
        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .error(e.getClass().getSimpleName())
                .code("ERR_UNKNOWN")
                .message("Unknown error occurred")
                .detailedMessage(e.getMessage())
                .path(request.getMethod().concat(":").concat(request.getRequestURI()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}