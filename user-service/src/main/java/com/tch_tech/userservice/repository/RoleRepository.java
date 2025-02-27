package com.tch_tech.userservice.repository;

import com.tch_tech.userservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleName(String roleName);
    Optional<UserRole> deleteUserRoleById(Long id);
}