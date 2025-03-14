package com.tch_tech.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tch_tech.accountservice.model.client_service.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String username;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> userRole = new HashSet<>();

//    public Set<Privilege> getPrivileges() {
//        return userRole.stream()
//                .flatMap(role -> role.getPrivileges().stream())
//                .collect(Collectors.toSet());
//    }
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Transient
    private UserProfile userProfile;
    private Long profileId;
}

