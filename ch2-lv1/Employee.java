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
    private final String position;
    private int annualSalary;
    private final LocalDate hireDate;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * @param employeeId   従業員ID
     * @param name         氏名
     * @param deptId       所属部署ID
     * @param position     役職名
     * @param annualSalary 年俸（円）
     * @param hireDate     入社日
     */
    public Employee(String employeeId, String name, String deptId,
                    String position, int annualSalary, LocalDate hireDate) {
        this.employeeId   = employeeId;
        this.name         = name;
        this.deptId       = deptId;
        this.position     = position;
        this.annualSalary = annualSalary;
        this.hireDate     = hireDate;
        this.active       = true;
        this.createdAt    = LocalDateTime.now();
        this.updatedAt    = LocalDateTime.now();
    }

    /** @return 従業員ID */
    public String getEmployeeId()  { return employeeId; }
    /** @return 氏名 */
    public String getName()        { return name; }
    /** @return 所属部署ID */
    public String getDeptId()      { return deptId; }
    /** @return 役職名 */
    public String getPosition()    { return position; }
    /** @return 年俸 */
    public int getAnnualSalary()   { return annualSalary; }
    /** @return 入社日 */
    public LocalDate getHireDate() { return hireDate; }
    /** @return 在籍中かどうか */
    public boolean isActive()      { return active; }
    /** @return 作成日時 */
    public LocalDateTime getCreatedAt() { return createdAt; }

    /**
     * 月額給与を計算して返す。
     *
     * @return 月額給与（年俸÷12）
     */
    public int calcMonthlySalary() {
        return annualSalary / 12;
    }

    /**
     * 退職処理を行う。
     */
    public void retire() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 従業員の基本情報を文字列で返す。
     *
     * @return 表示用文字列
     */
    protected String getSummary() {//privateをprotectedに変更
        return String.format("[%s] %s / %s / 月給：%,d円 / 在籍：%s",
                employeeId, name, position, calcMonthlySalary(),
                active ? "在籍" : "退職");
    }
}