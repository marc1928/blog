package com.tch_tech.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @ToString @NoArgsConstructor
@EqualsAndHashCode
public class Privilege {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegeId;
    private String privilegeName;
}
