package com.tch_tech.accountservice.controller;


import com.tch_tech.accountservice.dto.DtoRequest;
import com.tch_tech.accountservice.entity.Account;
import com.tch_tech.accountservice.service.AccountService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

// to get the logs out
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);

// ###################### add constructor for injection of dependencies ############################
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

// #################### Gestion des account ###########################
//  @PostAuthorize("hasAuthority('ADMIN')")

    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody Account account){
        logger.info("received request: {}", account);
        Account accountSaved = accountService.addNewAccount(account);
        return ResponseEntity.ok(accountSaved);
    }

    @GetMapping
    public List<Account> getAllUsers() {
        return accountService.listAllAccount();
    }

    @GetMapping("/{id}")
    public Optional<Account> getUserById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public Optional<Account> updateUser(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<Account> loadUserByUsername(@PathVariable String username){
        Optional<Account> user = accountService.loadAccountByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

// ################################# Role to Account #################################################
    @PostMapping("/rolesToUser")
    public void addRoleToUser(@RequestBody DtoRequest dtoRequest) {
        accountService.addRoleToUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }
    @PutMapping("/updateRole")
    public void updateRoleOfUser(@RequestBody DtoRequest dtoRequest) {
        accountService.updateRoleOfUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }

 // ########
    @GetMapping( "/profile")
    public Optional<Account> profile(Principal principal) {
        return accountService.loadAccountByUsername(principal.getName());
    }

}



