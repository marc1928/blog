package com.tch_tech.accountservice.controller;


import com.tch_tech.accountservice.dto.DtoRequest;
import com.tch_tech.accountservice.entity.Account;
import com.tch_tech.accountservice.service.AccountService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping
public class UserController {

    private final AccountService accountService;

// to get the logs out
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

// ###################### add constructor for injection of dependencies ############################
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

// #################### Gestion des account ###########################
    @PostMapping("/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Account> createUser(@RequestBody Account account){
        logger.info("received request: {}", account);
        Account accountSaved = accountService.addNewAccount(account);
        return ResponseEntity.ok(accountSaved);
    }

    @GetMapping("/users")
    public List<Account> getAllUsers() {
        return accountService.listAllAccount();
    }

    @GetMapping("/users/{id}")
    public Optional<Account> getUserById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
    @PutMapping("/users/{id}")
    public Optional<Account> updateUser(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
    @GetMapping("/users/by-name/{username}")
    public ResponseEntity<Account> loadUserByUsername(@PathVariable String username){
        Optional<Account> user = accountService.loadAccountByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


// ################################# Role to Account #################################################
    @PostMapping("/users/addRoleToUser")
    public void addRoleToUser(@RequestBody DtoRequest dtoRequest) {
        accountService.addRoleToUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }
    @PutMapping("/users/updateRoleOfUser")
    public void updateRoleOfUser(@RequestBody DtoRequest dtoRequest) {
        accountService.updateRoleOfUser(dtoRequest.getUsername(), dtoRequest.getRoleName());
    }

 // ########
    @GetMapping(path = "/profile")
    public Optional<Account> profile(Principal principal) {
        return accountService.loadAccountByUsername(principal.getName());
    }

}



