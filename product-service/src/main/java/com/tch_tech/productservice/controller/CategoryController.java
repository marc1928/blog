package com.tch_tech.productservice.controller;

import com.tch_tech.productservice.entity.Category;
import com.tch_tech.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1 = categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    @GetMapping("/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,@RequestBody Category category) {
        Category category2 = categoryService.updateCategory(categoryId, category)
                .orElseThrow(() -> new RuntimeException("Category not found during update."));
        return ResponseEntity.ok(category2);
    }
}
