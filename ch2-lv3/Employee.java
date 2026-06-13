// package com.example.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 従業員を表すエンティティクラス。
 */
public class Employee {

    private final String employeeId;
    private final String name;
    private final String deptId;
    private final String positionName;
    private int annualSalary;
    private final int allowance;
    private final LocalDate hireDate;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Employee(String employeeId, String name, String deptId,
                    String positionName, int annualSalary, int allowance,
                    LocalDate hireDate) {
        this.employeeId   = employeeId;
        this.name         = name;
        this.deptId       = deptId;
        this.positionName = positionName;
        this.annualSalary = annualSalary;
        this.allowance    = allowance;
        this.hireDate     = hireDate;
        this.active       = true;
        this.createdAt    = LocalDateTime.now();
        this.updatedAt    = LocalDateTime.now();
    }

    public String getEmployeeId()   { return employeeId; }
    public String getName()         { return name; }
    public String getDeptId()       { return deptId; }
    public String getPositionName() { return positionName; }
    public int getAnnualSalary()    { return annualSalary; }
    public int getAllowance()        { return allowance; }
    public LocalDate getHireDate()  { return hireDate; }
    public boolean isActive()       { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    /** @return 月額給与（年俸÷12 + 役職手当） */
    public int calcMonthlySalary() {
        return annualSalary / 12 + allowance;
    }

    public void retire() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
}