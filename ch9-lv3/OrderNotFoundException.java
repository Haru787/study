package com.example.order.exception;

public class OrderNotFoundException extends RuntimeException {

    private final Integer orderId;

    public OrderNotFoundException(Integer orderId) {
        super("注文が見つかりません。ID: " + orderId);
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
