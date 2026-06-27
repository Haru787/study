package com.example.billing;

/**
 * データベース操作に失敗した場合にスローされる例外。
 */
public class DatabaseException extends RuntimeException {

    private final String operation;

    /**
     * @param operation 失敗した操作名（例: "INSERT invoice"）
     * @param cause     原因となった例外
     */
    public DatabaseException(String operation, Throwable cause) {
        super("データベース操作に失敗しました: " + operation, cause);
        this.operation = operation;
    }

    /** @return 失敗した操作名 */
    public String getOperation() { return operation; }
}