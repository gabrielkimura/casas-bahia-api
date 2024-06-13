package com.example.casas_bahia_api.service.exception;

public class CpfCnpjIvalidException extends RuntimeException{

    public CpfCnpjIvalidException(String message) {
        super(message);
    }

    public CpfCnpjIvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
