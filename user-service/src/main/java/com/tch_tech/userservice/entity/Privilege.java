package com.tch_tech.userservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @ToString @NoArgsConstructor
@EqualsAndHashCode
@Table
public class Privilege {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String privilegeName;
    private String description;

}
