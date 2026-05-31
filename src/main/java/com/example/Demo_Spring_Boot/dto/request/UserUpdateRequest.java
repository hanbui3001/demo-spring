package com.example.Demo_Spring_Boot.dto.request;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String email, String displayName, Integer age
) {
}
