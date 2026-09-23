package com.example.point.exception;

public class InsufficientPointsException extends RuntimeException {

    private final Integer currentBalance;
    private final Integer requiredPoints;

    public InsufficientPointsException(Integer currentBalance, Integer requiredPoints) {
        super("ポイントが不足しています。現在残高: " + currentBalance + ", 必要ポイント: " + requiredPoints);
        this.currentBalance = currentBalance;
        this.requiredPoints = requiredPoints;
    }

    public Integer getCurrentBalance() { return currentBalance; }
    public Integer getRequiredPoints() { return requiredPoints; }
}
