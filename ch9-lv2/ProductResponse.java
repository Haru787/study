package com.example.product.dto.response;

import com.example.product.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    private Integer id;
    private String productCode;
    private String productName;
    private String category;
    private BigDecimal unitPrice;
    private Integer stockQuantity;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static ProductResponse from(ProductEntity entity) {
        ProductResponse response = new ProductResponse();
        response.id            = entity.getId();
        response.productCode   = entity.getProductCode();
        response.productName   = entity.getProductName();
        response.category      = entity.getCategory();
        response.unitPrice     = entity.getUnitPrice();
        response.stockQuantity = entity.getStockQuantity();
        response.description   = entity.getDescription();
        response.isActive      = entity.getIsActive();
        response.createdAt     = entity.getCreatedAt();
        return response;
    }

    public Integer getId() { return id; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public Integer getStockQuantity() { return stockQuantity; }
    public String getDescription() { return description; }
    public Boolean getIsActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
