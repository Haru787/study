// package com.example.order;

/**
 * 注文処理に関するカスタム例外クラス。
 */
public class InvalidOrderException extends RuntimeException {

    private final String orderCode;

    public InvalidOrderException(String orderCode, String message) {
        super("[注文コード: " + orderCode + "] " + message);
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }
}