package com.tch_tech.userservice.controller;

import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.service.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController {
    private UserServiceImpl userServiceImpl;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
// #################### Gestion des user ###########################
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        logger.info("received request: {}", user);
        User userSaved = userServiceImpl.addNewUser(user);
        return ResponseEntity.ok(userSaved);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userServiceImpl.listAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }
    @PutMapping("/users/{id}")
    public Optional<User> updateUser(@PathVariable Long id,@RequestBody User user) {
        return userServiceImpl.updateUser(id, user);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
    }


// ################################# Role to User ##########################################
    @PostMapping("/users/roles")
    public UserRole createRole(@RequestBody UserRole userRole) {
        return userServiceImpl.addNewUserRole(userRole);
    }

    public void addRoleToUser()



}
