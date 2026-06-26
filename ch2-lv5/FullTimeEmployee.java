// package com.example.employee;

import java.time.LocalDate;

/**
 * 正社員を表すクラス。
 */
public class FullTimeEmployee extends Employee {

    private final String positionName;
    private final int allowance;

    public FullTimeEmployee(String employeeId, String name, String deptId,
                            int annualSalary, LocalDate hireDate,
                            String positionName, int allowance) {
        super(employeeId, name, deptId, annualSalary, hireDate);
        this.positionName = positionName;
        this.allowance    = allowance;
    }

    /**
     * 月額給与 = 年俸÷12 + 役職手当
     */
    @Override
    public int calcMonthlySalary() {
        return annualSalary / 12 + allowance;
    }

    /**
     * 役職名を返す。
     *
     * @return 役職名
     */
    public String getPositionName() {//戻り値intをStringに変更
        return positionName;
    }

    public int getAllowance() { return allowance; }

    @Override
    public String getEmployeeType() { return "正社員"; }
}
