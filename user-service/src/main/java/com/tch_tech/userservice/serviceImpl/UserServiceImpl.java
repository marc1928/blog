package com.tch_tech.userservice.serviceImpl;

import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.repository.RoleRepository;
import com.tch_tech.userservice.repository.UserRepository;

import com.tch_tech.userservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
        
@Service
@Transactional
public class UserServiceImpl implements UserService {
// ############################ Déclaration des attributs ###########################

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    // ########### Injection des dependencies via le constructeur ######################"
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

// ##################### Gestion des users #############################""############
    @Override
    public User addNewUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        if (userRepository == null){
            return Collections.emptyList();
        }
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(
          existingUser -> {
              existingUser.setFirstName(user.getFirstName());
              existingUser.setLastName(user.getLastName());
              existingUser.setEmail(user.getEmail());
              existingUser.setUsername(user.getUsername());
              existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
              return userRepository.save(existingUser);
          }
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> loadUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
    


// ################################## Role to User #######################################
    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getUserRole().add(userRole);
        userRepository.save(user);
    }


    @Override
    public void updateRoleOfUser(String username, String roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getUserRole().clear();
        user.getUserRole().add(userRole);
        userRepository.save(user);
    }
    
}
