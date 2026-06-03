package com.example.Demo_Spring_Boot.service;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.dto.response.PageResponse;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception;
    UserDetailResponse login(UserLoginRequest userLoginRequest) throws Exception;
    UserDetailResponse getUserById(String Id) throws Exception;
   PageResponse<UserDetailResponse> getUserList(int page, int size, String email, String displayName) throws Exception;
    UserDetailResponse updateUser(String id, UserUpdateRequest userUpdateRequest) throws Exception;
    UserDetailResponse findUserById(String Id) throws Exception;
}
