package com.application.core.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private String mensaje;

    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
