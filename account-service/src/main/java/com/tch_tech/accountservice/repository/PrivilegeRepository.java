package com.tch_tech.accountservice.repository;

import com.tch_tech.accountservice.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByPrivilegeName(String privilegeName);
    boolean existsByPrivilegeName(String privilegeName);
}
