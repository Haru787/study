// package com.example.employee;

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

    /** 月額給与を返す（サブクラスに委ねる）。 */
    public abstract int calcMonthlySalary();

    /** 従業員種別名を返す（サブクラスに委ねる）。 */
    public abstract String getEmployeeType();

    /** 年俸を更新する。 */
    public void updateAnnualSalary(int newSalary) {
        this.annualSalary = newSalary;
        this.updatedAt    = LocalDateTime.now();
    }
}