package com.example.Demo_Spring_Boot.dto.response;

import lombok.Builder;

@Builder
public record UserRegisterResponse (
        String id, String email, String displayName, Integer age, String status
){
}
