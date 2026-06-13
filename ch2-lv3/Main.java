// package com.example.employee;

import java.time.LocalDate;

/**
 * 従業員管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        repository.saveDepartment(new Department("D01", "開発部", "東京本社", 30));
        repository.saveDepartment(new Department("D02", "営業部", "大阪支社", 20));

        repository.saveEmployee(new Employee("E001", "田中 一郎", "D01",
                "主任", 6600000, 50000, LocalDate.of(2019, 4, 1)));
        repository.saveEmployee(new Employee("E002", "鈴木 花子", "D02",
                "一般職", 5040000, 0, LocalDate.of(2022, 4, 1)));
        repository.saveEmployee(new Employee("E003", "高橋 三郎", "D02",//部署ID修正。D99は存在しない部署ID
                "一般職", 4800000, 0, LocalDate.of(2023, 7, 1)));
        repository.saveEmployee(new Employee("E004", "山田 美咲", "D01",
                "主任", 6000000, 50000, LocalDate.of(2020, 10, 1)));

        service.printPaySlips();

        System.out.printf("%n月額給与合計：%,d円%n", service.calcTotalMonthlySalary());
        System.out.printf("従業員数：%d名%n", service.getEmployeeCount());
    }
}
