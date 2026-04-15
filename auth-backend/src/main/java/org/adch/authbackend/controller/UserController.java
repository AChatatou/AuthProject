package org.adch.authbackend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.adch.authbackend.dto.UserDto;
import org.adch.authbackend.dto.UserRegisterRequest;
import org.adch.authbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController("/auth")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterRequest registerRequest, UriComponentsBuilder uriBuilder) {

        var userDto = userService.registerUser(registerRequest);
        var uri = uriBuilder.path("/users/me").buildAndExpand(userDto.id()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }
}
