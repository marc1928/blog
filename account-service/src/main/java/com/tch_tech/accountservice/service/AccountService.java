package com.tch_tech.accountservice.service;

import com.tch_tech.accountservice.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {


    Account addNewAccount(Account account);
    Optional<Account> updateAccount(Long accountId, Account account);
    void deleteAccount(Long accountId);
    List<Account> listAllAccount();
    Optional<Account> getAccountById(Long accountId);
    Optional<Account> loadAccountByUsername(String userName);


    void addRoleToUser(String username, String roleName);
    void updateRoleOfUser(String username, String roleName);


}
