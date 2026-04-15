package org.adch.authbackend.controller;

import org.adch.authbackend.dto.ErrorResponse;
import org.adch.authbackend.exceptions.EmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("email", exception.getMessage()));

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("internal", "Something went wrong"));
    }
}
