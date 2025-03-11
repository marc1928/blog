package com.tch_tech.accountservice.service;

import com.tch_tech.accountservice.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface RoleService {

    UserRole addNewUserRole(UserRole userRole);
    Optional<UserRole> updateUserRole(Long roleId, UserRole userRole);
    void deleteUserRole(Long roleId);
    Collection<UserRole> getUserRoles();

    void addPrivilegeToRole(String privilegeName, String roleName);

}
