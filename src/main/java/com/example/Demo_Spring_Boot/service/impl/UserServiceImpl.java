package com.example.Demo_Spring_Boot.service.impl;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.model.User;
import com.example.Demo_Spring_Boot.other.Status;
import com.example.Demo_Spring_Boot.repository.UserRepository;
import com.example.Demo_Spring_Boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception {
        if (userRepository.existsByEmail(userRegisterRequest.email())) {
            throw new Exception("Email Already Exists");
        }
        User user = User.builder()
                .email(userRegisterRequest.email())
                .password(bCryptPasswordEncoder.encode(userRegisterRequest.password()))
                .displayName(userRegisterRequest.displayName())
                .age(userRegisterRequest.age())
                .status(Status.ACTIVE)
                .build();
        userRepository.save(user);
        return UserRegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .age(user.getAge())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public UserDetailResponse login(UserLoginRequest request) throws Exception {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new Exception("Email Not Found"));
        if(!bCryptPasswordEncoder.matches(request.password(), user.getPassword())) {
            throw new Exception("Wrong Password");
        }
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .age(user.getAge())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public UserDetailResponse getUserById(String id) throws Exception {
        User user = userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .age(user.getAge())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public List<UserDetailResponse> getUserList() throws Exception {
        List<User> users = userRepository.findAll();
        return users.stream().map(
                user -> UserDetailResponse.builder()
                        .email(user.getEmail())
                        .displayName(user.getDisplayName())
                        .age(user.getAge())
                        .status(user.getStatus().toString())
                        .build()
        ).toList();
    }

    @Override
    public UserDetailResponse updateUser(String id, UserUpdateRequest userUpdateRequest) throws Exception {
        User user = userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
        user = User.builder()
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .age(user.getAge())
                .status(user.getStatus())
                .build();
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .age(user.getAge())
                .status(user.getStatus().toString())
                .build();
    }




}
