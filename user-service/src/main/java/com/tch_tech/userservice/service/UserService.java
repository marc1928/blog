package com.tch_tech.userservice.service;

import com.tch_tech.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User addNewUser(User user);
    Optional<User> updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> listAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> loadUserByUsername(String userName);


    void addRoleToUser(String username, String roleName);
    void updateRoleOfUser(String username, String roleName);


}
