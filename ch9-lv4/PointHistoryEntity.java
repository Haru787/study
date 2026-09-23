package com.example.point.entity;

import java.time.LocalDateTime;

public class PointHistoryEntity {

    private Integer id;
    private Integer memberId;
    private Integer pointChange;
    private String reason;
    private Integer balanceAfter;
    private LocalDateTime createdAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }

    public Integer getPointChange() { return pointChange; }
    public void setPointChange(Integer pointChange) { this.pointChange = pointChange; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Integer getBalanceAfter() { return balanceAfter; }
    public void setBalanceAfter(Integer balanceAfter) { this.balanceAfter = balanceAfter; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
