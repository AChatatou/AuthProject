package org.adch.authbackend.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super(String.format("A user with the email %s already exists", email));
    }
}
