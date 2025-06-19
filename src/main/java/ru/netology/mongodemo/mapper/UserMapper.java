package ru.netology.mongodemo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.netology.mongodemo.dto.UserRequestDto;
import ru.netology.mongodemo.dto.UserResponseDto;
import ru.netology.mongodemo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User dtoToUser(UserRequestDto dto);

    UserResponseDto userToDto(User user);
}