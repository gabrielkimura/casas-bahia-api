package com.example.casas_bahia_api.service.exception;

public class MatriculaAlreadyExistsException extends RuntimeException{
    public MatriculaAlreadyExistsException(String message) {
        super(message);
    }

    public MatriculaAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
