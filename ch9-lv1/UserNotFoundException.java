package com.example.user.exception;

public class UserNotFoundException extends RuntimeException {

    private final Integer userId;

    public UserNotFoundException(Integer userId) {
        super("ユーザーが見つかりません。ID: " + userId);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
