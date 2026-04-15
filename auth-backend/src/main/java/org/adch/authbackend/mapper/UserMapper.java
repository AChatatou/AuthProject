package org.adch.authbackend.mapper;

import org.adch.authbackend.dto.UserDto;
import org.adch.authbackend.dto.UserRegisterRequest;
import org.adch.authbackend.dto.UserUpdateRequest;
import org.adch.authbackend.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterRequest registerRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "newEmail")
    void updateUserFromRequest(UserUpdateRequest updateRequest, @MappingTarget User entity);

    UserDto toDto(User user);
}
