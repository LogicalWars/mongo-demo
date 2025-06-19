package ru.netology.mongodemo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "The name cannot be empty")
    @Size(min = 3, max = 50, message = "The name must have length between 3 and 50")
    private String name;

    @NotBlank(message = "The email cannot be empty")
    @Email
    private String email;

    @Min(value = 0, message = "The age cannot be lower, then 0")
    @Max(value = 130, message = "The age cannot be bigger, then 130")
    private int age;
}
