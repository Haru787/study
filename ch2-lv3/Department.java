// package com.example.employee;

import java.time.LocalDateTime;

/**
 * 部署を表すエンティティクラス。
 */
public class Department {

    private final String deptId;
    private final String deptName;
    private final String location;
    private final int headcount;
    private final LocalDateTime createdAt;

    public Department(String deptId, String deptName, String location, int headcount) {
        this.deptId    = deptId;
        this.deptName  = deptName;
        this.location  = location;
        this.headcount = headcount;
        this.createdAt = LocalDateTime.now();
    }

    public String getDeptId()   { return deptId; }
    public String getDeptName() { return deptName; }
    public String getLocation() { return location; }
    public int getHeadcount()   { return headcount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}