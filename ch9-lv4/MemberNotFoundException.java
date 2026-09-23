package com.example.point.exception;

public class MemberNotFoundException extends RuntimeException {

    private final Integer memberId;

    public MemberNotFoundException(Integer memberId) {
        super("会員が見つかりません。ID: " + memberId);
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }
}
