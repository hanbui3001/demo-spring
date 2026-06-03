package com.example.Demo_Spring_Boot.mapper;

import com.example.Demo_Spring_Boot.dto.request.UserRegisterRequest;
import com.example.Demo_Spring_Boot.dto.request.UserUpdateRequest;
import com.example.Demo_Spring_Boot.dto.response.UserDetailResponse;
import com.example.Demo_Spring_Boot.dto.response.UserRegisterResponse;
import com.example.Demo_Spring_Boot.model.User;
import com.example.Demo_Spring_Boot.other.Status;
import org.mapstruct.*;

@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserMapper {
    //register
    UserRegisterResponse toUserRegisterResponse(User user);
    User toUser(UserRegisterRequest request);
    @AfterMapping
    public default void updateStatusWhenRegister(UserRegisterRequest request, @MappingTarget User user){
        user.setStatus(Status.ACTIVE);
    }
    //

    UserDetailResponse toUserDetailResponse(User user);

    User updateUserFromDto(UserUpdateRequest request, @MappingTarget User user);
}
