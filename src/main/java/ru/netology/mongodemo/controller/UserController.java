package ru.netology.mongodemo.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netology.mongodemo.dto.UserRequestDto;
import ru.netology.mongodemo.dto.UserResponseDto;
import ru.netology.mongodemo.exception.UserNotFoundException;
import ru.netology.mongodemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService usersService;

    @RolesAllowed("ROLE_WRITE")
    @PostMapping()
    public ResponseEntity<UserResponseDto> addUsers(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto responseDto = usersService.createUser(userRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Secured("ROLE_READ")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> responseDto = usersService.getAllUsers();
        return ResponseEntity.ok().body(responseDto);
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        UserResponseDto responseDto = usersService.getUserById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostAuthorize("returnObject == authentication.principal.username")
    @GetMapping("/username")
    public String getUsername(@RequestParam String username) {
        return username;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDto> getUserByName(@PathVariable("name") String name) {
        UserResponseDto responseDto = usersService.getUserByName(name);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") String id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto responseDto = usersService.updateUser(id, userRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable("id") String id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleApiException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
