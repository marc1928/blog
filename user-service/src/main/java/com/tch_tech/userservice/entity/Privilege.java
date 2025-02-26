package com.tch_tech.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter @ToString @NoArgsConstructor
@EqualsAndHashCode
@Table(name = "privilege")
public class Privilege {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String privilegeId;
    private String privilegeName;
    private String description;
    @ManyToMany
    private Collection<UserRole> userRoles =new ArrayList<>();
}
