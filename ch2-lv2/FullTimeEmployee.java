package com.example.employee;

import java.time.LocalDate;

/**
 * 正社員を表すクラス。Employee を継承し、Position インターフェースを実装する。
 * ※ コンパイルエラー1: implements の記述が誤っている（Position → Postion）
 */
public class FullTimeEmployee extends Employee implements Position {//Postion → Position に修正

    private final String positionName;
    private final int allowance;
    private final int retirementBonusRate;

    /**
     * @param employeeId        従業員ID
     * @param name              氏名
     * @param deptId            所属部署ID
     * @param annualSalary      年俸（円）
     * @param hireDate          入社日
     * @param positionName      役職名
     * @param allowance         役職手当（月額）
     * @param retirementBonusRate 退職金倍率
     */
    public FullTimeEmployee(String employeeId, String name, String deptId,
                            int annualSalary, LocalDate hireDate,
                            String positionName, int allowance, int retirementBonusRate) {
        super(employeeId, name, deptId, annualSalary, hireDate);
        this.positionName      = positionName;
        this.allowance         = allowance;
        this.retirementBonusRate = retirementBonusRate;
    }

    /**
     * 月額給与 = 年俸÷12 + 役職手当
     */
    @Override
    public int calcMonthlySalary() {
        return annualSalary / 12 + allowance;
    }

    @Override
    public String getPositionName() { return positionName; }

    @Override
    public int getAllowance() { return allowance; }

    /**
     * 退職金を計算する。
     *
     * @return 退職金（月額給与 × 倍率）
     */
    public int calcRetirementBonus() {
        return calcMonthlySalary() * retirementBonusRate;
    }

    @Override
    public String getEmployeeType() { return "正社員"; }
}
