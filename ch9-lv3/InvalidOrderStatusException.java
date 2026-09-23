package com.example.order.exception;

public class InvalidOrderStatusException extends RuntimeException {

    private final String currentStatus;
    private final String requiredStatus;

    public InvalidOrderStatusException(String currentStatus, String requiredStatus) {
        super("注文ステータスが不正です。現在: " + currentStatus + ", 必要: " + requiredStatus);
        this.currentStatus  = currentStatus;
        this.requiredStatus = requiredStatus;
    }

    public String getCurrentStatus() { return currentStatus; }
    public String getRequiredStatus() { return requiredStatus; }
}
