// package com.example.employee;

import java.time.LocalDate;

/**
 * 契約社員を表すクラス。
 */
public class ContractEmployee extends Employee {

    private final int monthlyWage;
    private final int contractMonths;

    public ContractEmployee(String employeeId, String name, String deptId,
                            int monthlyWage, LocalDate hireDate, int contractMonths) {
        super(employeeId, name, deptId, (monthlyWage * 12), hireDate);//"CONTRACT"を年俸に変換
        this.monthlyWage     = monthlyWage;
        this.contractMonths  = contractMonths;
    }

    public int getMonthlyWage()     { return monthlyWage; }
    public int getContractMonths()  { return contractMonths; }

    @Override
    public int calcMonthlySalary() {
        return annualSalary / 12; // 年俸÷12で月額給与を計算に変更
    }

    @Override
    public String getEmployeeType() { return "契約社員"; }
}
