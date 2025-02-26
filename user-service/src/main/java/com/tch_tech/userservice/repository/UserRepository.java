package com.tch_tech.userservice.repository;

import com.tch_tech.userservice.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findById(@Nullable Long id);
    boolean existsByEmail(String email);
}
