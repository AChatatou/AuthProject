package org.adch.authbackend.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id %s does not exist", id.toString()));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email %s does not exist", email));
    }
}
