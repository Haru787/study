package com.example.point.dto.response;

import com.example.point.entity.PointHistoryEntity;

import java.time.LocalDateTime;

public class PointHistoryResponse {

    private Integer id;
    private Integer pointChange;
    private String reason;
    private Integer balanceAfter;
    private LocalDateTime createdAt;

    public static PointHistoryResponse from(PointHistoryEntity entity) {
        PointHistoryResponse response = new PointHistoryResponse();
        response.id           = entity.getId();
        response.pointChange  = entity.getPointChange();
        response.reason       = entity.getReason();
        response.balanceAfter = entity.getBalanceAfter();
        response.createdAt    = entity.getCreatedAt();
        return response;
    }

    public Integer getId() { return id; }
    public Integer getPointChange() { return pointChange; }
    public String getReason() { return reason; }
    public Integer getBalanceAfter() { return balanceAfter; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
