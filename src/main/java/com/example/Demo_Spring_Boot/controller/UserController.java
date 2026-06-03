package com.example.Demo_Spring_Boot.controller;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.ApiResponse;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.service.UserService;
import com.example.Demo_Spring_Boot.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) throws Exception {
        var data = userService.register(request);

        return ApiResponse.<UserRegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("register successfully")
                .data(data)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<UserDetailResponse> login(@RequestBody UserLoginRequest request) throws Exception {
        var data = userService.login(request);

        return ApiResponse.<UserDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("login successfully")
                .data(data)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDetailResponse> getUserById(@PathVariable String id) throws Exception {
        var data = userService.getUserById(id);

        return ApiResponse.<UserDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get user successfully")
                .data(data)
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<UserDetailResponse>> getUserList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "") String displayName
    ) throws Exception {
        var data = userService.getUserList(page, size, email, displayName);

        return ApiResponse.<PageResponse<UserDetailResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get user list successfully")
                .data(data)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDetailResponse> updateUser(
            @PathVariable String id,
            @RequestBody UserUpdateRequest request
    ) throws Exception {
        var data = userService.updateUser(id, request);

        return ApiResponse.<UserDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("update user successfully")
                .data(data)
                .build();
    }

    // projections
    @GetMapping("/v1/{id}")
    public ApiResponse<UserDetailResponse> getUserByIdV1(@PathVariable String id) throws Exception {
        var data = userService.findUserById(id);

        return ApiResponse.<UserDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get user successfully")
                .data(data)
                .build();
    }

}
