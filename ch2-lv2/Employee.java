package com.example.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 従業員を表す抽象基底クラス。
 */
public abstract class Employee {

    protected final String employeeId;
    protected final String name;
    protected final String deptId;
    protected int annualSalary;
    protected final LocalDate hireDate;
    protected boolean active;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    /**
     * @param employeeId   従業員ID
     * @param name         氏名
     * @param deptId       所属部署ID
     * @param annualSalary 年俸（円）
     * @param hireDate     入社日
     */
    public Employee(String employeeId, String name, String deptId,
                    int annualSalary, LocalDate hireDate) {
        this.employeeId   = employeeId;
        this.name         = name;
        this.deptId       = deptId;
        this.annualSalary = annualSalary;
        this.hireDate     = hireDate;
        this.active       = true;
        this.createdAt    = LocalDateTime.now();
        this.updatedAt    = LocalDateTime.now();
    }

    public String getEmployeeId()  { return employeeId; }
    public String getName()        { return name; }
    public String getDeptId()      { return deptId; }
    public int getAnnualSalary()   { return annualSalary; }
    public LocalDate getHireDate() { return hireDate; }
    public boolean isActive()      { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    /** 月額給与を返す（実装はサブクラスに委ねる）。 */
    public abstract int calcMonthlySalary();

    /** 従業員の種別名を返す（実装はサブクラスに委ねる）。 */
    public abstract String getEmployeeType();

    /** 退職処理を行う。 */
    public void retire() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
}