package com.tch_tech.userservice.serviceImpl;

import com.tch_tech.userservice.entity.Privilege;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.repository.PrivilegeRepository;
import com.tch_tech.userservice.repository.RoleRepository;
import com.tch_tech.userservice.service.RoleService;
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
    public Optional<UserRole> updateUserRole(Long id, UserRole userRole) {
        return roleRepository.findById(id).map(
                existingUserRole ->{
                    existingUserRole.setRoleName(userRole.getRoleName());
                    return roleRepository.save(existingUserRole);
                }
        );
    }

    @Override
    public void deleteUserRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Collection<UserRole> getUserRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addPrivilegeToRole(String privilegeName, String roleName) {
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Privilege privilege = privilegeRepository.findByPrivilegeName(privilegeName)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
        userRole.getPrivileges().add(privilege);
        roleRepository.save(userRole);
    }
}
