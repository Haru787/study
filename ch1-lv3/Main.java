// package com.example.order;

import java.util.List;

/**
 * 受発注管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        OrderRepository repository = new OrderRepository();

        Order order1 = new Order("ORD-2026-001", "C1001", "株式会社アルファ");
        order1.addItem(new OrderItem("ITEM-001", "ORD-2026-001",
                "PRD-101", "ノートPC 14インチ", 2, 128000));
        order1.addItem(new OrderItem("ITEM-002", "ORD-2026-001",
                "PRD-202", "ワイヤレスマウス", 2, 4800));
        order1.setNote("納品書3部同封");

        Order order2 = new Order("ORD-2026-002", "C1002", "ベータ工業株式会社");
        order2.addItem(new OrderItem("ITEM-003", "ORD-2026-002",
                "PRD-401", "モニター 27インチ", 4, 42000));
        order2.setNote("請求書別送");

        Order order3 = new Order("ORD-2026-003", "C1003", "ガンマ商事");
        order3.addItem(new OrderItem("ITEM-004", "ORD-2026-003",
                "PRD-305", "USB-Cハブ 7ポート", 6, 6500));
        order3.addItem(new OrderItem("ITEM-005", "ORD-2026-003",
                "PRD-203", "メカニカルキーボード", 6, 8900));

        repository.save(order1);
        repository.save(order2);
        repository.save(order3);

        List<Order> orders = repository.findAll();
        OrderSummaryReport report = new OrderSummaryReport();
        report.print(orders);
    }
}