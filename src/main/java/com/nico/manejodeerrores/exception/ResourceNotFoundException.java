package com.nico.manejodeerrores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //Esta clase devuelve un estado NOT_FOUND
public class ResourceNotFoundException extends RuntimeException{
    public static final Long serialVersionUId = 1L;// todas las clases tienen q tener un Serial version id

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
