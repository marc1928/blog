package com.tch_tech.productservice.serviceImpl;

import com.tch_tech.productservice.entity.Category;
import com.tch_tech.productservice.entity.Product;
import com.tch_tech.productservice.repository.CategoryRepository;
import com.tch_tech.productservice.repository.ProductRepository;
import com.tch_tech.productservice.service.ProductService;
import com.tch_tech.productservice.specification.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
// ############################################################################################

    @Override
    public Product addProduct(Product product) {
        if ( productRepository.findByProductName(product.getProductName()).isPresent()){
            throw new IllegalArgumentException("Product with this name already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long ProductId) {
        return productRepository.findById(ProductId);
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        return productRepository.findById(productId).map(
                existingProduct -> {
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setUnit(product.getUnit());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setDescription(product.getDescription());
                    return productRepository.save(existingProduct);
                }
        );
    }

    @Override
    public void deleteProductById(Long ProductId) {
        productRepository.deleteById(ProductId);
    }

    @Override
    public void addCategoryToProduct(String categoryName, String productName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public List<Product> searchProducts(String productName, String categoryName, String description, Double minPrice, Double maxPrice) {
        // Construire une spécification dynamique combinée
        Specification<Product> specification = Specification
                .where(ProductSpecifications.hasName(productName))
                .and(ProductSpecifications.hasCategory(categoryName))
                .and(ProductSpecifications.hasDescription(description))
                .and(ProductSpecifications.hasPrice(minPrice, maxPrice));

        // Utiliser `findAll` de JpaSpecificationExecutor pour trouver les produits correspondants
        return productRepository.findAll(specification);

    }


}
