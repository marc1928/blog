package com.tch_tech.userservice.repository;

import com.tch_tech.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    boolean existsByEmail(String email);
}

//    Optional<User> findByEmail(String email);
//    Optional<User> findById(@Nullable Long id);
//    boolean existsByEmail(String email);