package com.tch_tech.accountservice.serviceImpl;

import com.tch_tech.accountservice.entity.Account;
import com.tch_tech.accountservice.entity.UserRole;
import com.tch_tech.accountservice.repository.RoleRepository;
import com.tch_tech.accountservice.repository.AccountRepository;

import com.tch_tech.accountservice.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
        
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
// ############################ Déclaration des attributs ###########################

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

// ############## Injection des dependencies via le constructeur ######################
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

// ##################### Gestion des users #############################""############
    @Override
    public Account addNewAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Account already exists");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccount() {
        if (accountRepository == null){
            return Collections.emptyList();
        }
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Optional<Account> updateAccount(Long id, Account account) {
        return accountRepository.findById(id).map(
          existingUser -> {
              existingUser.setEmail(account.getEmail());
              existingUser.setUsername(account.getUsername());
              existingUser.setPassword(passwordEncoder.encode(account.getPassword()));
              return accountRepository.save(existingUser);
          }
        );
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }


    @Override
    public Optional<Account> loadAccountByUsername(String userName) {

        return accountRepository.findByUsername(userName);
    }
    


// ################################## Role to Account #######################################
    @Override
    public void addRoleToUser(String username, String roleName) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        account.getUserRole().add(userRole);
        accountRepository.save(account);
    }


    @Override
    public void updateRoleOfUser(String username, String roleName) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        UserRole userRole = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        account.getUserRole().clear();
        account.getUserRole().add(userRole);
        accountRepository.save(account);
    }
    
}
