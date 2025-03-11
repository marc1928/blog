package com.tch_tech.productservice.service;

import com.tch_tech.productservice.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long categoryId);
    Optional<Category> updateCategory(Long categoryId, Category category);
    void deleteCategoryById(Long categoryId);
}
