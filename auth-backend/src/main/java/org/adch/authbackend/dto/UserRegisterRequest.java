package org.adch.authbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @NotBlank(message = "Name is required")  String name,
        @Email(message = "Invalid E-mail")  String email,
        @NotBlank @Size(min = 6, message = "Password must be at least 6 characters long") String password) {
}
