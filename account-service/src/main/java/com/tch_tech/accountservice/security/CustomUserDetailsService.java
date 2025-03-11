package com.tch_tech.accountservice.security;

import com.tch_tech.accountservice.entity.Account;
import com.tch_tech.accountservice.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    public CustomUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Récupère l'utilisateur par son username depuis la base de données
        Account account = accountService.loadAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        // Convertir les rôles de l'utilisateur en GrantedAuthority
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        account.getUserRole().forEach(role -> authorities.add(
                new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));

        // Retourne un objet UserDetails avec les informations nécessaires
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                authorities);
    }
}
