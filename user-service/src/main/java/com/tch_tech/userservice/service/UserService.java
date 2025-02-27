package com.tch_tech.userservice.service;

import com.tch_tech.userservice.entity.Privilege;
import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User addNewUser(User user);
    Optional<User> updateUser(Long id, User user);
    void deleteUserById(Long id);
    List<User> listAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> loadUserByUsername(String userName);

    void addRoleToUser(String username, String roleName);
    void deleteRoleFromUser(String username, String roleName);
    void updateRoleToUser(String username, String roleName);

    UserRole addNewUserRole(UserRole userRole);
    Optional<UserRole> updateUserRole(Long id, UserRole userRole);
    void deleteUserRole(UserRole userRole);
    Collection<UserRole> getUserRoles();

    Privilege addNewPrivilege(Privilege privilege);
    Optional<Privilege> updatePrivilege(Long id, Privilege privilege);
    void deletePrivilege(Privilege privilege);
    Collection<Privilege> getPrivileges();

    void addPrivilegeToRole(String privilegeName, String roleName);
    Collection<Privilege> getPrivilegesByRole(String roleName);
}
