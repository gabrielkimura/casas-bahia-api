package com.example.casas_bahia_api.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcpetionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExcpetionHandler.class);

    @ExceptionHandler(MatriculaAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(MatriculaAlreadyExistsException matriculaException){
        return new ResponseEntity<>("Matricula ja existe", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MatriculaNotFoundException.class)
    public ResponseEntity<String> handleMatriculaNotFound(MatriculaNotFoundException matriculaNotFoundException) {
        return new ResponseEntity<>("Matrícula não encontrada", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TipoContratacaoInvalidaException.class)
    public ResponseEntity<String> handleTipoContratacaoInvalida(TipoContratacaoInvalidaException tipoContratacaoInvalidaException){
        return new ResponseEntity<>("Tipo de contratação inválida", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CpfCnpjIvalidException.class)
    public ResponseEntity<String> handleCpfCnpjInvalido(CpfCnpjIvalidException cpfCnpjIvalidException){
        return new ResponseEntity<>("Cpf ou Cnpj inválido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleEmailInvalido(InvalidEmailException invalidEmailException){
        return new ResponseEntity<>("Email inválido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FilialNotFoundException.class)
    public ResponseEntity<String> handleFilialNotFound(FilialNotFoundException filialNotFoundException){
        return new ResponseEntity<>("Filial não encontrada", HttpStatus.NOT_FOUND);
    }

}
