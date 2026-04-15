package org.adch.authbackend.dto;

public record UserUpdateRequest(String name, String oldEmail, String newEmail) {
}
