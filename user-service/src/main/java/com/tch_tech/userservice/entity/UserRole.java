package com.tch_tech.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name = "roles")
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String roleName;
    @ManyToMany
    private Collection<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges = new ArrayList<>();
}
