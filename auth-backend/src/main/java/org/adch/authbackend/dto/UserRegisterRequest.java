package org.adch.authbackend.dto;

public record UserRegisterRequest(String name, String email, String password) {
}
