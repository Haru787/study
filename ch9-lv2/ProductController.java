package com.example.product.controller;

import com.example.product.dto.request.ProductCreateRequest;
import com.example.product.dto.request.ProductUpdateRequest;
import com.example.product.dto.response.ProductListResponse;
import com.example.product.dto.response.ProductResponse;
import com.example.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer id) {
        logger.info("GET /api/products/{}", id);
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<ProductListResponse> findAll() {
        logger.info("GET /api/products");
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ProductListResponse> findByCategory(@PathVariable String category) {
        logger.info("GET /api/products/category/{}", category);
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> register(
            @RequestBody @Valid ProductCreateRequest request) {
        logger.info("POST /api/products");
        ProductResponse response = productService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Integer id,
            @RequestBody @Valid ProductUpdateRequest request) {
        logger.info("PUT /api/products/{}", id);
        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("DELETE /api/products/{}", id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
