package ru.netology.mongodemo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.mongodemo.dto.UserRequestDto;
import ru.netology.mongodemo.dto.UserResponseDto;
import ru.netology.mongodemo.exception.UserNotFoundException;
import ru.netology.mongodemo.mapper.UserMapper;
import ru.netology.mongodemo.model.User;
import ru.netology.mongodemo.repository.UserRepository;
import ru.netology.mongodemo.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.userToDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.userToDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.dtoToUser(userRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.userToDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        User updatedUser = userMapper.dtoToUser(userRequestDto);
        updatedUser.setId(id);

        User savedUser = userRepository.save(updatedUser);
        return userMapper.userToDto(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}
