// package com.example.order;

/**
 * 注文処理に関するカスタム例外クラス。
 * 不正な注文データが検出された場合にスローされる。
 */
public class InvalidOrderException extends RuntimeException {

    private final String orderCode;

    /**
     * @param orderCode エラーが発生した注文コード
     * @param message   エラーメッセージ
     */
    public InvalidOrderException(String orderCode, String message) {
        super("[注文コード: " + orderCode + "] " + message);
        this.orderCode = orderCode;
    }

    /**
     * @return エラーが発生した注文コード
     */
    public String getOrderCode() {
        return orderCode;
    }
}