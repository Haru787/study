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

        Order order3 = new Order("ORD-2026-003", "C1003", "ガンマ商事");
        order3.addItem(new OrderItem("ITEM-004", "ORD-2026-003",
                "PRD-305", "USB-Cハブ 7ポート", 5, 6500));
        // order3.addItem(null);
        order3.addItem(new OrderItem("ITEM-005", "ORD-2026-003",
                "PRD-203", "メカニカルキーボード", 5, 8900));

        service.accept(order1);
        service.accept(order2);
        service.accept(order3);

        service.printAllItems();

        System.out.printf("%n全明細の平均単価：%,.0f円%n", service.calcAverageItemPrice());
        System.out.printf("受注件数：%d件   総売上：%,d円%n",
                service.getOrderCount(), service.calcGrandTotal());
    }
}