package com.example.Demo_Spring_Boot.controller;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.ApiResponse;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRegisterResponse>> register(@RequestBody UserRegisterRequest request) throws Exception {
        var data =  userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<UserRegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("register successfully")
                        .data(data)
                        .build()
        );
    }
    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<UserDetailResponse>> login(@RequestBody UserLoginRequest request) throws Exception {
        var data =  userService.login(request);
        return ResponseEntity.ok().body(
                ApiResponse.<UserDetailResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("login successfully")
                        .data(data)
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUserById(@PathVariable String id) throws Exception {
        var data = userService.getUserById(id);
        return ResponseEntity.ok().body(
                ApiResponse.<UserDetailResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("get user successfully")
                        .data(data)
                        .build()
        );
    }
    @GetMapping("/list")
    public List<UserDetailResponse> getUserList() throws Exception {
        return userService.getUserList();
    }
    @PutMapping("/{id}")
    public UserDetailResponse updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) throws Exception {
        return userService.updateUser(id, request);
    }

}
