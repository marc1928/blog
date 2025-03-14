package com.tch_tech.productservice.serviceImpl;

import com.tch_tech.productservice.entity.Category;
import com.tch_tech.productservice.repository.CategoryRepository;
import com.tch_tech.productservice.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

// ######################################################################################

    @Override
    public Category addCategory(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()){
            throw new IllegalArgumentException("Category with this name already exists");
        }else {
            return categoryRepository.save(category);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public Optional<Category> updateCategory(Long categoryId, Category category) {
        return categoryRepository.findByCategoryId(categoryId).map(
                existingCategory -> {
                    existingCategory.setCategoryName(category.getCategoryName());
                    return categoryRepository.save(existingCategory);
                }
        );
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.findByCategoryId(categoryId);
    }
}
