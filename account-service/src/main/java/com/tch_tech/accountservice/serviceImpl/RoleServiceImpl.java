package com.tch_tech.accountservice.serviceImpl;

import com.tch_tech.accountservice.entity.Privilege;
import com.tch_tech.accountservice.entity.UserRole;
import com.tch_tech.accountservice.repository.PrivilegeRepository;
import com.tch_tech.accountservice.repository.RoleRepository;
import com.tch_tech.accountservice.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;


    public RoleServiceImpl(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public UserRole addNewUserRole(UserRole userRole) {
        if (roleRepository.existsByRoleName(userRole.getRoleName())) {
            throw new RuntimeException("Role already exists");
        }
        return roleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> updateUserRole(Long roleId, UserRole userRole) {
        return roleRepository.findById(roleId).map(
                existingUserRole ->{
                    existingUserRole.setRoleName(userRole.getRoleName());
                    return roleRepository.save(existingUserRole);
                }
        );
    }

    @Override
    public void deleteUserRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Collection<UserRole> getUserRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addPrivilegeToRole(String privilegeName, String roleName) {
        if (privilegeName == null || privilegeName.isEmpty()) {
            throw new IllegalArgumentException("Privilege name cannot be null or empty");
        }

        if (roleName == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        // Charger le rôle
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Charger ou créer le privilège
        Privilege privilege = privilegeRepository.findByPrivilegeName(privilegeName)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));


        userRole.getPrivileges().add(privilege);
        roleRepository.save(userRole);
    }
}
