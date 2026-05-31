package com.example.Demo_Spring_Boot.dto.response;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        int statusCode, String message, T data
) {
}
