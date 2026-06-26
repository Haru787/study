// package com.example.sales;

import java.time.LocalDateTime;

/**
 * 販売商品を表すエンティティクラス。
 */
public class Product implements Priceable {

    private String productId;
    private String productName;
    private String category;
    private int unitPrice;
    private int stock;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 商品コンストラクタ。
     */
    public Product(String productId, String productName, String category,
                   int unitPrice, int stock) {
        this.productId   = productId;
        this.productName = productName;
        this.category    = category;
        this.unitPrice   = unitPrice;
        this.stock       = stock;
        this.active      = true;
        this.createdAt   = LocalDateTime.now();
        this.updatedAt   = LocalDateTime.now();
    }

    @Override
    public int getUnitPrice() { return unitPrice; }

    @Override
    public int calcTotalPrice() { return unitPrice * stock; }

    public String getProductId()   { return productId; }
    public String getProductName() { return productName; }
    public String getCategory()    { return category; }
    public int    getStock()       { return stock; }
    public boolean isActive()      { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setStock(int stock)         { this.stock = stock;     this.updatedAt = LocalDateTime.now(); }
    public void setActive(boolean active)   { this.active = active;   this.updatedAt = LocalDateTime.now(); }
}