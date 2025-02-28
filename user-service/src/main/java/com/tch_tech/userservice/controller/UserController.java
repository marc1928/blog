package com.tch_tech.userservice.controller;

import com.tch_tech.userservice.dto.DtoRequest;
import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

// to get the logs out
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

// ###################### add constructor for injection of dependencies ############################
    public UserController(UserService userService) {
        this.userService = userService;
    }

// #################### Gestion des user ###########################
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        logger.info("received request: {}", user);
        User userSaved = userService.addNewUser(user);
        return ResponseEntity.ok(userSaved);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PutMapping("/users/{id}")
    public Optional<User> updateUser(@PathVariable Long id,@RequestBody User user) {
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @GetMapping("/users/by-name/{username}")
    public ResponseEntity<User> loadUserByUsername(@PathVariable String username){
        Optional<User> user = userService.loadUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


// ################################# Role to User #################################################
    @PostMapping("/users/addRoleToUser")
    public void addRoleToUser(@RequestBody DtoRequest dtoRequest) {
        userService.addRoleToUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }
    @PutMapping("/users/updateRoleOfUser")
    public void updateRoleOfUser(@RequestBody DtoRequest dtoRequest) {
        userService.updateRoleOfUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }



}
