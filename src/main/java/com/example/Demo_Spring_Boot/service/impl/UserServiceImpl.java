package com.example.Demo_Spring_Boot.service.impl;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.exception.CustomException;
import com.example.Demo_Spring_Boot.exception.ErrorCode;
import com.example.Demo_Spring_Boot.mapper.UserMapper;
import com.example.Demo_Spring_Boot.model.User;
import com.example.Demo_Spring_Boot.other.Status;
import com.example.Demo_Spring_Boot.repository.projection.NameOnly;
import com.example.Demo_Spring_Boot.repository.UserRepository;
import com.example.Demo_Spring_Boot.repository.specification.UserSpecification;
import com.example.Demo_Spring_Boot.service.UserService;
import com.example.Demo_Spring_Boot.dto.response.PageResponse;
import com.example.Demo_Spring_Boot.utils.PageResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserMapper userMapper;
    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception {
        if (userRepository.existsByEmail(userRegisterRequest.email())) {
            throw new CustomException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(userRegisterRequest);
        userRepository.save(user);
        return userMapper.toUserRegisterResponse(user);
    }

    @Override
    public UserDetailResponse login(UserLoginRequest request) throws Exception {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new CustomException(ErrorCode.INVALID_CREDENTIALS));
        if(!bCryptPasswordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }
        return userMapper.toUserDetailResponse(user);
    }

    @Override
    public UserDetailResponse getUserById(String id) throws Exception {
        User user = userRepository.findUserById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserDetailResponse(user);
    }

    @Override
    public PageResponse<UserDetailResponse> getUserList(int page, int size, String email, String displayName) throws Exception {
        page = PageResponseUtil.normalizePage(page);
        size = PageResponseUtil.normalizePageSize(size);
        Pageable pageable = PageRequest.of(page -1 , size);
        Specification<User> userSpecification = Specification.where(UserSpecification.hasEmail(email)).and(UserSpecification.hasDisplayName(displayName));
        Page<User> users = userRepository.findAll(userSpecification, pageable);
        List<UserDetailResponse> data = users.getContent()
                .stream()
                .map(userMapper::toUserDetailResponse).toList();

        return PageResponse.<UserDetailResponse>builder()
                .currentPage(users.getNumber())
                .pageSize(users.getSize())
                .totalPages(users.getTotalPages())
                .total(users.getTotalElements())
                .data(data)
                .build();

    }

    @Override
    public UserDetailResponse updateUser(String id, UserUpdateRequest userUpdateRequest) throws Exception {
        User user = userRepository.findUserById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user = userMapper.updateUserFromDto(userUpdateRequest, user);
        return userMapper.toUserDetailResponse(userRepository.save(user));
    }

    @Override
    public UserDetailResponse findUserById(String Id) throws Exception {
        NameOnly nameOnly = userRepository.findById(Id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return UserDetailResponse.builder()
                .displayName(nameOnly.getDisplayName())
                .age(nameOnly.getAge())
                .build();
    }


}
