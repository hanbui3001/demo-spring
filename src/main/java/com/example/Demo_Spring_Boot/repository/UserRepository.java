package com.example.Demo_Spring_Boot.repository;

import com.example.Demo_Spring_Boot.dto.request.UserLoginRequest;
import com.example.Demo_Spring_Boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findUserById(String id);
}
