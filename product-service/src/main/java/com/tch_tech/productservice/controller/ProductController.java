package com.tch_tech.productservice.controller;

import com.tch_tech.productservice.dto.DtoRequest;
import com.tch_tech.productservice.entity.Product;
import com.tch_tech.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
// #####################################################################################
    // add new product
    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        Product product1 = productService.addProduct(product);
        return ResponseEntity.ok(product1);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = productService.getProductById(productId);

        return product
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // return 404 not found
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product) {
        Product product2 = productService.updateProduct(productId, product)
                .orElseThrow(() -> new RuntimeException("Product not found during update."));
        return ResponseEntity.ok(product2);
    }
    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
    }
    @PostMapping("/add-category")
    public void addCategoryToProduct(@RequestBody DtoRequest dtoRequest){
        productService.addCategoryToProduct(dtoRequest.getCategoryName(), dtoRequest.getProductName());
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        // Appeler le service pour effectuer une recherche dynamique
        return productService.searchProducts(productName, categoryName, description, minPrice, maxPrice);
    }

}
