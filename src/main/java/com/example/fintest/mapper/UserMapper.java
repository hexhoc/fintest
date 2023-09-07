package com.example.fintest.mapper;

import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.controller.dto.UserResponse;
import com.example.fintest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Маппер сущности пользователя.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toDto(User entity);

    User toEntity(UserRequest dto);

}
