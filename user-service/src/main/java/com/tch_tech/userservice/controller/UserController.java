package com.tch_tech.userservice.controller;

import com.tch_tech.userservice.entity.Privilege;
import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.service.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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


// ################################# Gestion des Role ##########################################
    @PostMapping("/roles")
    public UserRole createRole(@RequestBody UserRole userRole) {
        return userServiceImpl.addNewUserRole(userRole);
    }
    @GetMapping("/roles")
    public Collection<UserRole> getListRole() {
        return userServiceImpl.getUserRoles();
    }

    @PutMapping("/roles/{id}")
    public Optional<UserRole> updateUserRole(@PathVariable Long id,@RequestBody UserRole userRole) {
        return userServiceImpl.updateUserRole(id, userRole);
    }
    @DeleteMapping("/roles/{id}")
    public void deleteUserRole(Long id, UserRole userRole){
        userServiceImpl.deleteUserRole(id);
    }


// ################################# Role to User #################################################


// ################################# Privilege ####################################################
    @PostMapping("/privilege")
    public Privilege createPrivilege(@RequestBody Privilege privilege) {
        return userServiceImpl.addNewPrivilege(privilege);
    }
    @PostMapping("/privileges/{id}")
    public Optional<Privilege> updatePrivilege(@PathVariable Long id,@RequestBody Privilege privilege) {
        return userServiceImpl.updatePrivilege(id, privilege);
    }
    @DeleteMapping("/privileges/{id}")
    public void deletePrivilege(@PathVariable Long id) {
        userServiceImpl.deletePrivilege(id);
    }
    @GetMapping("/privileges")
    public Collection<Privilege> getListPrivilege() {
        return userServiceImpl.getAllPrivileges();
    }
    @GetMapping("/privileges/{id}")
    public Collection<Privilege> getListPrivilege(@PathVariable Long id) {
        return userServiceImpl.getPrivileges(id);
    }
}
