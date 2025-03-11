package com.tch_tech.accountservice.serviceImpl;

import com.tch_tech.accountservice.entity.Privilege;
import com.tch_tech.accountservice.repository.PrivilegeRepository;
import com.tch_tech.accountservice.service.PrivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege addNewPrivilege(Privilege privilege) {
        if(privilegeRepository.existsByPrivilegeName(privilege.getPrivilegeName())){
            throw new RuntimeException("Privilege already exists");
        }
        return privilegeRepository.save(privilege);
    }

    @Override
    public Optional<Privilege> updatePrivilege(Long privilegeId, Privilege privilege) {
        return privilegeRepository.findById(privilegeId).map(
                existingPrivilege -> {
                    existingPrivilege.setPrivilegeName(privilege.getPrivilegeName());
                    return privilegeRepository.save(existingPrivilege);
                }
        );
    }

    @Override
    public void deletePrivilege(Long privilegeId) {
        privilegeRepository.findById(privilegeId);
    }

    @Override
    public Collection<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    @Override
    public Collection<Privilege> getPrivileges(Long privilegeId) {
        return privilegeRepository.findAll();
    }
}
