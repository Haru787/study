// package com.example.sales;

import java.time.LocalDateTime;

/**
 * 販売管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        SalesRepository repo = new SalesRepository();

        LocalDateTime base = LocalDateTime.of(2026, 5, 25, 9, 0);

        repo.add(new SalesRecord("S001", "PRD-101", "田中 一郎",  5, 12800, "直販",   base.plusHours(1)));
        repo.add(new SalesRecord("S002", "PRD-102", "鈴木 花子",  3, 54000, "代理店", base.plusHours(2)));
        repo.add(new SalesRecord("S003", "PRD-103", "田中 一郎",  8,  3200, "EC",     base.plusHours(3)));
        repo.add(new SalesRecord("S004", "PRD-101", "高橋 三郎",  2, 12800, "直販",   base.plusHours(4)));
        repo.add(new SalesRecord("S005", "PRD-104", "鈴木 花子", 10,  1500, "EC",     base.plusHours(5)));

        SalesReport report = new SalesReport(repo);
        report.printDailySummary();
    }
}