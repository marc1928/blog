package com.tch_tech.accountservice.controller;

import com.tch_tech.accountservice.entity.Privilege;
import com.tch_tech.accountservice.service.PrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;

    }

    @PostMapping("/privileges")
    public ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilege) {
        Privilege privilegeSaved = privilegeService.addNewPrivilege(privilege);
        return ResponseEntity.ok(privilegeSaved);
    }
    @PutMapping("/privileges/{id}")
    public Optional<Privilege> updatePrivilege(@PathVariable Long id, @RequestBody Privilege privilege) {
        return privilegeService.updatePrivilege(id, privilege);
    }
    @DeleteMapping("/privileges/{id}")
    public void deletePrivilege(@PathVariable Long id) {
        privilegeService.deletePrivilege(id);
    }

    @GetMapping("/privileges")
    public Collection<Privilege> getListPrivilege() {
        return privilegeService.getAllPrivileges();
    }

    @GetMapping("/privileges/by-id/{id}")
    public Collection<Privilege> getListPrivilege(@PathVariable Long id) {
        return privilegeService.getPrivileges(id);
    }
}
