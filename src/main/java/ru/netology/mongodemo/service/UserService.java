package ru.netology.mongodemo.service;

import ru.netology.mongodemo.dto.UserRequestDto;
import ru.netology.mongodemo.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(String id);
    UserResponseDto getUserByName(String name);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(String id, UserRequestDto userRequestDto);
    void deleteUser(String id);
}
