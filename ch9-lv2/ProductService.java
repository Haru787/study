package com.example.product.service;

import com.example.product.dto.request.ProductCreateRequest;
import com.example.product.dto.request.ProductUpdateRequest;
import com.example.product.dto.response.ProductListResponse;
import com.example.product.dto.response.ProductResponse;
import com.example.product.entity.ProductEntity;
import com.example.product.exception.DuplicateProductCodeException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.repository.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;

    public ProductResponse findById(Integer id) {
        logger.info("商品検索: id={}", id);
        return productMapper.findById(id)
                .map(ProductResponse::from)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductListResponse findAll() {
        logger.info("全商品取得");
        List<ProductResponse> list = productMapper.findAll().stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
        return new ProductListResponse(list);
    }

    public ProductListResponse findByCategory(String category) {
        logger.info("カテゴリ別商品取得: category={}", category);
        List<ProductResponse> list = productMapper.findByCategory(category).stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
        return new ProductListResponse(list);
    }

    @Transactional
    public ProductResponse register(ProductCreateRequest request) {
        logger.info("商品登録: productCode={}", request.getProductCode());
        if (productMapper.countByProductCode(request.getProductCode()) > 0) {
            throw new DuplicateProductCodeException(request.getProductCode());
        }
        ProductEntity entity = new ProductEntity();
        entity.setProductCode(request.getProductCode());
        entity.setProductName(request.getProductName());
        entity.setCategory(request.getCategory());
        entity.setUnitPrice(request.getUnitPrice());
        entity.setStockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0);
        entity.setSupplierId(request.getSupplierId());
        entity.setDescription(request.getDescription());
        entity.setIsActive(true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        productMapper.insert(entity);
        return ProductResponse.from(entity);
    }

    @Transactional
    public ProductResponse update(Integer id, ProductUpdateRequest request) {
        logger.info("商品更新: id={}", id);
        ProductEntity entity = productMapper.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        entity.setProductName(request.getProductName());
        entity.setCategory(request.getCategory());
        entity.setUnitPrice(request.getUnitPrice());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setDescription(request.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        productMapper.update(entity);
        return ProductResponse.from(entity);
    }

    @Transactional
    public void delete(Integer id) {
        logger.info("商品削除（論理）: id={}", id);
        productMapper.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productMapper.deactivate(id);
    }
}
