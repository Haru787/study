package com.example.employee;

import java.time.LocalDate;

/**
 * 従業員管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        FullTimeEmployee ft1 = new FullTimeEmployee(
                "E001", "田中 一郎", "D01",
                7200000, LocalDate.of(2018, 4, 1),
                "課長", 50000, 24);

        FullTimeEmployee ft2 = new FullTimeEmployee(
                "E002", "鈴木 花子", "D02",
                5400000, LocalDate.of(2021, 4, 1),
                "一般職", 0, 12);

        ContractEmployee ct1 = new ContractEmployee(
                "C001", "高橋 三郎", "D01",
                350000, LocalDate.of(2025, 10, 1), 6);

        Employee[] employees = { ft1, ft2, ct1 };

        System.out.println("===== 従業員一覧 =====");
        for (Employee emp : employees) {
            System.out.printf("[%s] %-12s  種別：%-6s  月給：%,d円%n",
                    emp.getEmployeeId(),
                    emp.getName(),
                    emp.getEmployeeType(),
                    emp.calcMonthlySalary()
            );
        }

        System.out.printf("%n[%s] %s の退職金見込み：%,d円%n",
                ft1.getEmployeeId(), ft1.getName(), ft1.calcRetirementBonus());
    }
}
