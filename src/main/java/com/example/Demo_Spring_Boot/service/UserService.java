package com.example.Demo_Spring_Boot.service;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;

import java.util.List;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception;
    UserDetailResponse login(UserLoginRequest userLoginRequest) throws Exception;
    UserDetailResponse getUserById(String Id) throws Exception;
    List<UserDetailResponse> getUserList() throws Exception;
    UserDetailResponse updateUser(String id, UserUpdateRequest userUpdateRequest) throws Exception;
}
