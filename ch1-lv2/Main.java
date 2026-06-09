// package com.example.order;

/**
 * 受発注管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        OrderRepository repository = new OrderRepository();
        OrderService service = new OrderService(repository);

        Order order1 = new Order("ORD-2026-001", "C1001", "株式会社アルファ");
        order1.addItem(new OrderItem("ITEM-001", "ORD-2026-001",
                "PRD-101", "ノートPC 14インチ", 2, 128000));
        order1.addItem(new OrderItem("ITEM-002", "ORD-2026-001",
                "PRD-202", "ワイヤレスマウス", 2, 4800));

        Order order2 = new Order("ORD-2026-002", "C1002", "ベータ工業株式会社");
        order2.addItem(new OrderItem("ITEM-003", "ORD-2026-002",
                "PRD-401", "モニター 27インチ", 4, 42000));

        Order order3 = new Order("ORD-2026-003", "C1001", "株式会社アルファ");
        order3.addItem(new OrderItem("ITEM-004", "ORD-2026-003",
                "PRD-305", "USB-Cハブ 7ポート", 5, 6500));

        service.accept(order1);
        service.accept(order2);
        service.accept(order3);

        service.ship("ORD-2026-001");

        service.printSummary();

        System.out.printf("C1001 合計：%,d円%n", service.calcCustomerTotal("C1001"));
    }
}