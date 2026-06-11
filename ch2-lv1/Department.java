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
    private LocalDateTime updatedAt;

    /**
     * @param deptId    部署ID
     * @param deptName  部署名
     * @param location  勤務地
     * @param headcount 定員人数
     */
    public Department(String deptId, String deptName, String location, int headcount) {
        this.deptId    = deptId;
        this.deptName  = deptName;
        this.location  = location;
        this.headcount = headcount;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /** @return 部署ID */
    public String getDeptId()   { return deptId; }
    /** @return 部署名 */
    public String getDeptName() { return deptName; }
    /** @return 勤務地 */
    public String getLocation() { return location; }
    /** @return 定員人数 */
    public int getHeadcount()   { return headcount; }
    /** @return 作成日時 */
    public LocalDateTime getCreatedAt() { return createdAt; }
}
