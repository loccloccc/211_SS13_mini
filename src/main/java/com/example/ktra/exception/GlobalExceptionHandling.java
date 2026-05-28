package com.example.ktra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {
    // Validation Error
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> {
                        errors.put(error.getField(), error.getDefaultMessage());
                    });

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // 404 Not Found
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        // 500 Internal Error
        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleException(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
