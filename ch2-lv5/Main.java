// package com.example.employee;

import java.time.LocalDate;
import java.util.List;

/**
 * 従業員管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        EmployeeRepository repository = new EmployeeRepository();
        SalaryRevisionService service = new SalaryRevisionService(repository);

        repository.saveDepartment(new Department("D01", "開発部", "東京本社", 30));
        repository.saveDepartment(new Department("D02", "営業部", "大阪支社", 20));

        repository.saveEmployee(new FullTimeEmployee(
                "E001", "田中 一郎", "D01",
                6600000, LocalDate.of(2019, 4, 1), "主任", 50000));
        repository.saveEmployee(new FullTimeEmployee(
                "E002", "鈴木 花子", "D02",
                5400000, LocalDate.of(2021, 4, 1), "一般職", 0));
        repository.saveEmployee(new ContractEmployee(
                "C001", "高橋 三郎", "D01",
                400000, LocalDate.of(2025, 10, 1), 6));

        // 改定前の月額給与を保存
        List<Employee> employees = repository.findAllEmployees();
        int[] beforeSalaries = new int[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            beforeSalaries[i] = employees.get(i).calcMonthlySalary();
        }

        // 8% 昇給
        service.applyRaise(0.08);

        service.printRevisionReport(beforeSalaries);
        service.printDeptSummary();

        System.out.printf("%n平均月額給与：%,.0f円  従業員数：%d名%n",
                service.calcAverageMonthlySalary(), service.getEmployeeCount());
    }
}
