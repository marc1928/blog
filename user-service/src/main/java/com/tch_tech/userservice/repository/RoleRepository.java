package com.tch_tech.userservice.repository;

import com.tch_tech.userservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
//    Optional<UserRole> deleteUserRoleById(Long id);
}