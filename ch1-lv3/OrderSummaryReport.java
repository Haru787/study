// package com.example.order;

import java.util.List;
import java.util.Optional;

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
        System.out.println("===== 受発注管理システム 集計レポート =====");

        int grandTotal = 0;
        for (Order order : orders) {
            int subtotal = order.calcTotal();
            grandTotal += subtotal;

            System.out.printf("[%s] %-24s  明細数：%d件   小計：%,d円%n",
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getItems().size(),
                    subtotal
            );

            String note = Optional.ofNullable(order.getNote()).map(String::trim).orElse("");
            System.out.println("  備考：" + note.trim());
        }

        System.out.println("-------------------------------------------");
        System.out.printf("受注件数：%d件   総合計：%,d円%n", orders.size(), grandTotal);
    }
}