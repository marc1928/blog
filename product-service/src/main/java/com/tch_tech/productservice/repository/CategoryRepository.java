package com.tch_tech.productservice.repository;

import com.tch_tech.productservice.entity.Category;
import com.tch_tech.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Product> {

    Optional<Category> findByCategoryName(String categoryName);

    Optional<Category> findByCategoryId(Long categoryId);
}
