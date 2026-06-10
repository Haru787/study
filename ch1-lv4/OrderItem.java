// package com.example.order;

import java.time.LocalDateTime;

/**
 * 注文明細エンティティ。
 */
public class OrderItem {

    private final String itemId;
    private final String orderId;
    private final String productCode;
    private final String productName;
    private int quantity;
    private final int unitPrice;
    private boolean cancelled;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem(String itemId, String orderId, String productCode,
                     String productName, int quantity, int unitPrice) {
        this.itemId      = itemId;
        this.orderId     = orderId;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity    = quantity;
        this.unitPrice   = unitPrice;
        this.cancelled   = false;
        this.createdAt   = LocalDateTime.now();
        this.updatedAt   = LocalDateTime.now();
    }

    public String getItemId()      { return itemId; }
    public String getOrderId()     { return orderId; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public int    getQuantity()    { return quantity; }
    public int    getUnitPrice()   { return unitPrice; }
    public boolean isCancelled()   { return cancelled; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public int calcSubtotal() {
        if (cancelled) return 0;
        return quantity * unitPrice;
    }

    public void cancel() {
        this.cancelled = true;
        this.updatedAt = LocalDateTime.now();
    }
}