// package com.example.employee;

import java.time.LocalDate;

/**
 * 従業員管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        Department dept1 = new Department("D01", "開発部", "東京本社", 30);
        Department dept2 = new Department("D02", "営業部", "大阪支社", 20);

        Employee emp1 = new Employee("E001", "田中 一郎", "D01",
                "主任", 6000000, LocalDate.of(2020, 4, 1));
        Employee emp2 = new Employee("E002", "鈴木 花子", "D02",
                "一般職", 4800000, LocalDate.of(2022, 7, 1));
        Employee emp3 = new Employee("E003", "佐藤 次郎", "D01",
                "一般職", 4200000, LocalDate.of(2023, 4, 1));

        System.out.println("===== 従業員一覧 =====");
        System.out.println(emp1.getSummary());
        System.out.println(emp2.getSummary());
        System.out.println(emp3.getSummary());

        System.out.printf("%n部署：%s（%s）  定員：%d名%n",
                dept1.getDeptName(), dept1.getLocation(), dept1.getHeadcount());
    }
}
