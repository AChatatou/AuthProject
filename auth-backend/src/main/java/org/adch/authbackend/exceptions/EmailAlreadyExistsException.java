package org.adch.authbackend.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(String.format("The E-mail %s is currently assigned to another user", email));
    }
}
