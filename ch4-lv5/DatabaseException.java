package com.example.billing;

/**
 * データベース操作に失敗した場合にスローされる例外。
 */
public class DatabaseException extends RuntimeException {

    private final String operation;

    public DatabaseException(String operation, Throwable cause) {
        super("DB操作失敗: " + operation, cause);
        this.operation = operation;
    }

    public String getOperation() { return operation; }
}