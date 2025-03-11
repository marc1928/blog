package com.tch_tech.productservice.service;

import com.tch_tech.productservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long ProductId);
    Optional<Product> updateProduct(Long productId, Product product);
    void deleteProductById(Long ProductId);

    void addCategoryToProduct(String categoryName, String productName);

    List<Product> searchProducts(String productName, String categoryName, String description, Double minPrice, Double maxPrice);

}
