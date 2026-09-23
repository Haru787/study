package com.example.stock.exception;

public class InsufficientStockException extends RuntimeException {

    private final Integer currentQuantity;
    private final Integer requiredQuantity;

    public InsufficientStockException(Integer currentQuantity, Integer requiredQuantity) {
        super("在庫が不足しています。現在数量: " + currentQuantity + ", 必要数量: " + requiredQuantity);
        this.currentQuantity  = currentQuantity;
        this.requiredQuantity = requiredQuantity;
    }

    public Integer getCurrentQuantity() { return currentQuantity; }
    public Integer getRequiredQuantity() { return requiredQuantity; }
}
