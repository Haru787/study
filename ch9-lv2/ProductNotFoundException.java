package com.example.product.exception;

public class ProductNotFoundException extends RuntimeException {

    private final Integer productId;

    public ProductNotFoundException(Integer productId) {
        super("商品が見つかりません。ID: " + productId);
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }
}
