package com.example.Demo_Spring_Boot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder

public record UserRegisterRequest(
        @NotBlank(message = "email is required")
        String email,
        @NotBlank(message = "password is required")
        @Size(min = 6, message = "password must be longer than 6 characters")
        String password,
        @NotBlank(message = "name is required")
        String displayName,
        Integer age
) {
}
