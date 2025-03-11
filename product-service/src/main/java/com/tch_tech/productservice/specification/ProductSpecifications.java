package com.tch_tech.productservice.specification;

import com.tch_tech.productservice.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    // Filtrer par nom du produit
    public static Specification<Product> hasName(String productName) {
        return (root, query, criteriaBuilder) ->
                productName == null ? null : criteriaBuilder.like(root.get("productName"), "%" + productName + "%");
    }

    // Filtrer par catégorie
    public static Specification<Product> hasCategory(String categoryName) {
        return (root, query, criteriaBuilder) ->
                categoryName == null ? null : criteriaBuilder.like(root.get("category").get("categoryName"), "%" + categoryName + "%");
    }

    // Filtrer par description
    public static Specification<Product> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                description == null ? null : criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    // Filtrer par plage de prix
    public static Specification<Product> hasPrice(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            } else if (minPrice != null && maxPrice != null) {
                // Si les deux limites sont définies
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if (minPrice != null) {
                // Si seule la limite inférieure est définie
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                // Si seule la limite supérieure est définie
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
        };
    }
}