package com.example.Demo_Spring_Boot.dto.request;

import lombok.*;

@Builder

public record UserRegisterRequest(
        String email, String password, String displayName, Integer age
) {
}
