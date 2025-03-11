package com.tch_tech.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table // (name = "user_roles")
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(nullable = false, unique = true)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Privilege> privileges = new ArrayList<>();

}
