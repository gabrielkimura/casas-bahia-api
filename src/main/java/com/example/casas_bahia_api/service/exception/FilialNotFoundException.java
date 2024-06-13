package com.example.casas_bahia_api.service.exception;

public class FilialNotFoundException extends RuntimeException{

    public FilialNotFoundException(String message) {
        super(message);
    }

    public FilialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
