package com.example.Demo_Spring_Boot.repository;

import com.example.Demo_Spring_Boot.model.User;
import com.example.Demo_Spring_Boot.repository.projection.NameOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    Optional<NameOnly> findById(String id);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findUserById(String id);
    @Query("select u from User u where (:email is null or lower(u.email) like lower(concat('%', :email, '%') )" +
            "and (:displayName is null or lower(u.displayName) like lower(concat('%', :displayName, '%') ) ) )")
    Page<User> searchUsers(String email, String displayName, Pageable pageable);
}
