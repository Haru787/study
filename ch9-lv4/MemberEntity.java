package com.example.point.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberEntity {

    private Integer id;
    private String memberCode;
    private String name;
    private String email;
    private String phone;
    private Integer pointBalance;
    private String rank;
    private LocalDate joinedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMemberCode() { return memberCode; }
    public void setMemberCode(String memberCode) { this.memberCode = memberCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getPointBalance() { return pointBalance; }
    public void setPointBalance(Integer pointBalance) { this.pointBalance = pointBalance; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public LocalDate getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDate joinedAt) { this.joinedAt = joinedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
