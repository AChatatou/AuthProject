package org.adch.authbackend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adch.authbackend.dto.UserDto;
import org.adch.authbackend.dto.UserRegisterRequest;
import org.adch.authbackend.dto.UserUpdateRequest;
import org.adch.authbackend.exceptions.EmailAlreadyExistsException;
import org.adch.authbackend.exceptions.UserNotFoundException;
import org.adch.authbackend.mapper.UserMapper;
import org.adch.authbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper mapper;

    public UserDto fetchUserById(UUID id) {
        var user =  userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        log.info("Fetched user with id {}", id);
        return mapper.toDto(user);
    }

    public UserDto fetchUserByEmail(String email) throws UserNotFoundException {
        var user =  userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
        log.info("Fetched user with email {}", email);
        return mapper.toDto(user);
    }


    public UserDto registerUser(UserRegisterRequest registerRequest) throws EmailAlreadyExistsException {

        var email = registerRequest.email();

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailAlreadyExistsException(email);
        });

        var newUser =  userRepository.save(mapper.toEntity(registerRequest));

        log.info("New user {} registered successfully. User id: {}", newUser.getName(), newUser.getId());

        return mapper.toDto(newUser);
    }

    public UserDto updateUser(UserUpdateRequest updateRequest) throws UserNotFoundException, EmailAlreadyExistsException {

        var user = userRepository.findByEmail(updateRequest.oldEmail())
                .orElseThrow(() -> new UserNotFoundException(updateRequest.oldEmail()));

        if (userRepository.findByEmail(updateRequest.newEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(updateRequest.newEmail());
        }

        mapper.updateUserFromRequest(updateRequest, user);

        var updatedUser = userRepository.save(user);

        log.info("User data updated successfully. Name: {}, Email: {}", updatedUser.getName(), updatedUser.getEmail());

        return mapper.toDto(updatedUser);
    }

    public void deleteUser(UUID id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        log.info("User {} deleted successfully", id);
    }



}
