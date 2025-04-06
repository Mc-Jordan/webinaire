package com.chilo_tech.gestion_webinaire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionApplication {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeExcetion(RuntimeException runtimeException){
        return new ResponseEntity<>(Map.of("message",runtimeException.getMessage(),"code d'erreur",400), HttpStatus.BAD_REQUEST);
    }
}
