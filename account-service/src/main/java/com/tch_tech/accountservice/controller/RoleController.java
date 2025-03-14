package com.tch_tech.accountservice.controller;

import com.tch_tech.accountservice.dto.DtoRequest;
import com.tch_tech.accountservice.entity.UserRole;
import com.tch_tech.accountservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api/roles")
@RestController
public class RoleController {
    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;

    }

    @PostMapping
    public ResponseEntity<UserRole> createRole(@RequestBody UserRole userRole) {
        UserRole userRole1 = roleService.addNewUserRole(userRole);
        return ResponseEntity.ok(userRole1);
    }
    @GetMapping
    public Collection<UserRole> getListRole() {
        return roleService.getUserRoles();
    }

    @PutMapping("/{id}")
    public Optional<UserRole> updateUserRole(@PathVariable Long id, @RequestBody UserRole userRole) {
        return roleService.updateUserRole(id, userRole);
    }
    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable Long id){
        roleService.deleteUserRole(id);
    }

    @PostMapping("/privilegeToRole")
    public void privilegeToRole(@RequestBody DtoRequest dtoRequest){
        roleService.addPrivilegeToRole(dtoRequest.getRoleName(), dtoRequest.getPrivilegeName());
    }

}
