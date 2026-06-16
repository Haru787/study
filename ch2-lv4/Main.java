// package com.example.employee;

import java.time.LocalDate;

/**
 * 従業員管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        EmployeeRepository repository = new EmployeeRepository();
        EvaluationService service = new EvaluationService(repository);

        repository.save(new Employee("E001", "田中 一郎", "D01",
                "主任", 6600000, LocalDate.of(2019, 4, 1)));
        repository.save(new Employee("E002", "鈴木 花子", "D02",
                "一般職", 5400000, LocalDate.of(2021, 4, 1)));
        repository.save(new Employee("E003", "高橋 三郎", "D01",
                "一般職", 4800000, LocalDate.of(2023, 4, 1)));

        service.addRecord(new EvaluationRecord("E001", "2026-Q1", 88));
        service.addRecord(new EvaluationRecord("E001", "2026-Q2", 76));
        service.addRecord(new EvaluationRecord("E002", "2026-Q1", 92));
        service.addRecord(new EvaluationRecord("E999", "2026-Q1", 65));
        service.addRecord(new EvaluationRecord("E002", "2026-Q2", 85));
        service.addRecord(new EvaluationRecord("E003", "2026-Q1", 61));
        service.addRecord(new EvaluationRecord("E003", "2026-Q2", 74));

        service.printScores();
        service.printAverageByEmployee();

        System.out.printf("%n全体最高スコア：%d点  評価レコード数：%d件%n",
                service.findMaxScore(), service.getRecordCount());
    }
}