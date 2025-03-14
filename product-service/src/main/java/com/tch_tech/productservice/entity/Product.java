package com.tch_tech.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Product {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String description;

    private BigDecimal price;

    private String unit;

    @ManyToOne
    private Category category;

}
