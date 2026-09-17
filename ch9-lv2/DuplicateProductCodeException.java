package com.example.product.exception;

public class DuplicateProductCodeException extends RuntimeException {

    private final String productCode;

    public DuplicateProductCodeException(String productCode) {
        super("商品コードが既に存在します: " + productCode);
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }
}
