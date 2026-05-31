package com.example.Demo_Spring_Boot.dto.request;

import lombok.Builder;

@Builder
public record UserLoginRequest (
        String email, String password
){
}
