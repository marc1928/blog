package com.tch_tech.userservice.repository;

import com.tch_tech.userservice.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByPrivilegeName(String privilegeName);
    Collection<Privilege> findPrivilegeByRole(String roleName);
    Collection<Privilege> findPrivilegeById(Long id);
}
