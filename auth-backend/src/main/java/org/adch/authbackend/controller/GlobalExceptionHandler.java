package org.adch.authbackend.controller;

import org.adch.authbackend.dto.ErrorResponse;
import org.adch.authbackend.exceptions.EmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("email", exception.getMessage()));

    }


    public ResponseEntity<List<ErrorResponse>> handleValidationErrors(MethodArgumentNotValidException exception) {

        var errors = new ArrayList<ErrorResponse>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.add(new ErrorResponse(error.getField(), error.getDefaultMessage())) );

        return ResponseEntity.badRequest().body(errors);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGeneric(RuntimeException ex) {

        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("internal", "Something went wrong"));
    }
}
