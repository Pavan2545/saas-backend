package com.example.saas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
        Map<String, String> m = new HashMap<>();
        m.put("error", ex.getMessage());
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> m = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> m.put(err.getField(), err.getDefaultMessage()));
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }
}
