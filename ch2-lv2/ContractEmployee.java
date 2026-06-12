package com.example.employee;

import java.time.LocalDate;

/**
 * 契約社員を表すクラス。Employee を継承する。
 * ※ コンパイルエラー2: calcMonthlySalary の戻り値型が void になっている
 * ※ コンパイルエラー3: contractEndDate フィールドへの代入で型ミスがある
 */
public class ContractEmployee extends Employee {

    private final int monthlyWage;
    private final int contractMonths;
    private final String contractEndDate;

    /**
     * @param employeeId      従業員ID
     * @param name            氏名
     * @param deptId          所属部署ID
     * @param monthlyWage     月額賃金（円）
     * @param hireDate        契約開始日
     * @param contractMonths  契約期間（月数）
     */
    public ContractEmployee(String employeeId, String name, String deptId,
                            int monthlyWage, LocalDate hireDate, int contractMonths) {
        super(employeeId, name, deptId, monthlyWage * 12, hireDate);
        this.monthlyWage      = monthlyWage;
        this.contractMonths   = contractMonths;
        this.contractEndDate  = "12345";//文字列に変更
    }

    /** @return 月額賃金 */
    public int getMonthlyWage() { return monthlyWage; }
    /** @return 契約期間（月数） */
    public int getContractMonths() { return contractMonths; }
    /** @return 契約終了予定日（文字列） */
    public String getContractEndDate() { return contractEndDate; }

    /**
     * 月額給与を返す（契約社員は月額賃金をそのまま返す）。
     */
    @Override
    public int calcMonthlySalary() {//戻り値の型を int に変更
        return monthlyWage;
    }

    @Override
    public String getEmployeeType() { return "契約社員"; }
}
