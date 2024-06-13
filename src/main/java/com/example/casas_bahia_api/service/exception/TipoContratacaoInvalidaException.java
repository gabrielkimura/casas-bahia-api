package com.example.casas_bahia_api.service.exception;

import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisAnnos;

public class TipoContratacaoInvalidaException extends RuntimeException {

    public TipoContratacaoInvalidaException(String message) {
        super(message);
    }

    public TipoContratacaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
