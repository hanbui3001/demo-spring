package com.example.Demo_Spring_Boot.dto.response;

import lombok.Builder;

@Builder
public record UserDetailResponse (
        String id, String email, String displayName, Integer age, String status
){

}
