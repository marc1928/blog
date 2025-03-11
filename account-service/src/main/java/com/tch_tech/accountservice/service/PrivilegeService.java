package com.tch_tech.accountservice.service;

import com.tch_tech.accountservice.entity.Privilege;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface PrivilegeService {
    Privilege addNewPrivilege(Privilege privilege);
    Optional<Privilege> updatePrivilege(Long privilegeId, Privilege privilege);
    void deletePrivilege(Long privilegeId);
    Collection<Privilege> getAllPrivileges();
    Collection<Privilege> getPrivileges(Long privilegeId);
}
