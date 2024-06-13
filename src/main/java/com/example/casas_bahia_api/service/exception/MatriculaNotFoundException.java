package com.example.casas_bahia_api.service.exception;

public class MatriculaNotFoundException extends RuntimeException{

    public MatriculaNotFoundException(String message) {
        super(message);
    }

    public MatriculaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
