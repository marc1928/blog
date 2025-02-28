package com.tch_tech.userservice.service;

import com.tch_tech.userservice.entity.UserRole;

import java.util.Collection;
import java.util.Optional;

public interface RoleService {

    UserRole addNewUserRole(UserRole userRole);
    Optional<UserRole> updateUserRole(Long id, UserRole userRole);
    void deleteUserRole(Long id);
    Collection<UserRole> getUserRoles();

    void addPrivilegeToRole(String privilegeName, String roleName);

}
