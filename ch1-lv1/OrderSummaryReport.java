// package com.example.order;

import java.util.List;

/**
 * 注文サマリーレポートを出力するクラス。
 */
public class OrderSummaryReport {

    /**
     * 注文リストのサマリーを標準出力に表示する。
     *
     * @param orders レポート対象の注文リスト
     */
    public void print(List<Order> orders) {
        System.out.println("========================================");
        System.out.println("         受発注管理システム - 注文一覧");
        System.out.println("========================================");
        System.out.printf("%-12s  %-10s  %-16s  %s%n",
                "注文ID", "顧客ID", "顧客名", "合計金額");
        System.out.println("----------------------------------------");

        int grandTotal = 0;
        for (Order order : orders) {
            int total = order.calcTotal();
            grandTotal += total;
            System.out.printf("%-12s  %-10s  %-16s  %,d円%n",
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getCustomerName(),
                    total
            );
        }

        System.out.println("----------------------------------------");
        System.out.printf("受注件数：%d件   総合計：%,d円%n", orders.size(), grandTotal);
        System.out.println("========================================");
    }
}