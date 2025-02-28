package com.tch_tech.userservice.service;

import com.tch_tech.userservice.entity.Privilege;

import java.util.Collection;
import java.util.Optional;

public interface PrivilegeService {
    Privilege addNewPrivilege(Privilege privilege);
    Optional<Privilege> updatePrivilege(Long id, Privilege privilege);
    void deletePrivilege(Long id);
    Collection<Privilege> getAllPrivileges();
    Collection<Privilege> getPrivileges(Long id);
}
