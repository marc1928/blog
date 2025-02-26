package com.tch_tech.userservice.service;

import com.tch_tech.userservice.entity.Privilege;
import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.repository.PrivilegeRepository;
import com.tch_tech.userservice.repository.RoleRepository;
import com.tch_tech.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
// ############################ Déclaration des attributs ###########################
    private PrivilegeRepository privilegeRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private User user;

// ### Injection des dépendances avec @Override ##### Gestion des users ###############
    @Override
    public User addNewUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(
          existingUser -> {
              existingUser.setFirstName(user.getFirstName());
              existingUser.setLastName(user.getLastName());
              existingUser.setEmail(user.getEmail());
              existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
              return userRepository.save(existingUser);
          }
        );
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> loadUserByUsername(String userName) {
        return userRepository.findUserByUsername(userName);
    }

 // ############################# Gestion des roles d####################################
    @Override
    public UserRole addNewUserRole(UserRole userRole) {
        return roleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> updateUserRole(Long id, UserRole userRole) {
        return roleRepository.findById(id).map(
                existingUserRole ->{
                    existingUserRole.setRoleName(userRole.getRoleName());
                    return roleRepository.save(existingUserRole);
                }
        );
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        roleRepository.delete(userRole);
    }

    @Override
    public Collection<UserRole> getUserRoles() {
        return roleRepository.findAll();
    }

// ################################## Role to User #######################################
    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getUserRole().add(userRole);
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(String username, String roleName) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getUserRole().remove(userRole);
        userRepository.save(user);
    }

    @Override
    public void updateRoleToUser(String username, String roleName) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getUserRole().clear();
        user.getUserRole().add(userRole);
        userRepository.save(user);
    }

// ############################# Gestion des privilege ####################################
    @Override
    public Privilege addNewPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Optional<Privilege> updatePrivilege(Long id, Privilege privilege) {
        return privilegeRepository.findById(id).map(
                existingPrivilege -> {
                    existingPrivilege.setPrivilegeName(privilege.getPrivilegeName());
                    existingPrivilege.setDescription(privilege.getDescription());
                    return privilegeRepository.save(existingPrivilege);
                }
        );
    }

    @Override
    public void deletePrivilege(Privilege privilege) {
        privilegeRepository.delete(privilege);
    }

    @Override
    public Collection<Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }
// ################################### privilege to role #############################################
    @Override
    public void addPrivilegeToRole(String privilegeName, String roleName) {
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Privilege privilege = privilegeRepository.findByPrivilegeName(privilegeName)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
        userRole.getPrivileges().add(privilege);
        roleRepository.save(userRole);
    }

    @Override
    public Collection<Privilege> getPrivilegesByRole(String roleName) {
        return privilegeRepository.findPrivilegeByRole(roleName);
    }

}
