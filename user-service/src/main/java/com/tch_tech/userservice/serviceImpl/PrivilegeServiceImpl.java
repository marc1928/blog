package com.tch_tech.userservice.serviceImpl;

import com.tch_tech.userservice.entity.Privilege;
import com.tch_tech.userservice.repository.PrivilegeRepository;
import com.tch_tech.userservice.service.PrivilegeService;
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
    public void deletePrivilege(Long id) {
        privilegeRepository.findById(id);
    }

    @Override
    public Collection<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    @Override
    public Collection<Privilege> getPrivileges(Long id) {
        return privilegeRepository.findAll();
    }
}
