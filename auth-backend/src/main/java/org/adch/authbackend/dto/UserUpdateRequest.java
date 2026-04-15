package org.adch.authbackend.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        String name,
        @Email String oldEmail,
        @Email String newEmail) {
}
