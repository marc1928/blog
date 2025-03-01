package com.tch_tech.userservice.security;

import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.service.UserService;
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

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Récupère l'utilisateur par son username depuis la base de données
        User user = userService.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Convertir les rôles de l'utilisateur en GrantedAuthority
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getUserRole().forEach(role -> authorities.add(
                new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));

        // Retourne un objet UserDetails avec les informations nécessaires
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
