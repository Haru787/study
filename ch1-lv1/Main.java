// package com.example.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 受発注管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        Order order1 = new Order("ORD-2026-001", "C1001", "株式会社アルファ");
        order1.addItem(new OrderItem("ITEM-001", "ORD-2026-001",
                "PRD-101", "ノートPC 14インチ", 3, 128000));
        order1.addItem(new OrderItem("ITEM-002", "ORD-2026-001",
                "PRD-202", "ワイヤレスマウス", 3, 4800));
        order1.addItem(new OrderItem("ITEM-003", "ORD-2026-001",
                "PRD-305", "USB-Cハブ 7ポート", 3, 6500));

        Order order2 = new Order("ORD-2026-002", "C1002", "ベータ工業株式会社");
        order2.addItem(new OrderItem("ITEM-004", "ORD-2026-002",
                "PRD-101", "ノートPC 14インチ", 5, 128000));
        order2.addItem(new OrderItem("ITEM-005", "ORD-2026-002",
                "PRD-401", "モニター 27インチ", 5, 42000));

        Order order3 = new Order("ORD-2026-003", "C1003", "ガンマ商事");
        order3.addItem(new OrderItem("ITEM-006", "ORD-2026-003",
                "PRD-202", "ワイヤレスマウス", 10, 4800));
        order3.addItem(new OrderItem("ITEM-007", "ORD-2026-003",
                "PRD-203", "メカニカルキーボード", 10, 8900));//文字列："8900"を数値に修正

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        OrderSummaryReport report = new OrderSummaryReport();
        report.print(orders);
    }
}