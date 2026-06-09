// package com.example.order;

import java.util.List;

/**
 * 受発注業務ロジックを集約するサービスクラス。
 */
public class OrderService {

    private OrderRepository repository;

    /**
     * @param repository 注文リポジトリ
     */
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /**
     * 注文を受付登録する。
     *
     * @param order 登録する注文
     */
    public void accept(Order order) {
        order.setStatus("受付");
        repository.save(order);
    }

    /**
     * 注文を出荷済みに更新する。
     *
     * @param orderId 出荷する注文ID
     */
    public void ship(String orderId) {
        Order order = repository.findById(orderId);
        if (order == null) {
            throw new InvalidOrderException(orderId, "注文が見つかりません");
        }
        order.setStatus("出荷済");
    }

    /**
     * 指定顧客の注文合計金額を集計する。
     *
     * @param customerId 顧客ID
     * @return 合計金額
     */
    public int calcCustomerTotal(String customerId) {
        List<Order> orders = repository.findByCustomerId(customerId);
        int total = 0;
        for (Order order : orders) {
            total += order.calcTotal();
        }
        return total;
    }

    /**
     * 全注文の総売上金額を集計する。
     *
     * @return 総売上金額
     */
    public int calcGrandTotal() {
        List<Order> orders = repository.findAll();
        int total = 0;
        for (Order order : orders) {
            total += order.calcTotal();
        }
        return total;
    }

    /**
     * 登録件数を返す。
     *
     * @return 件数
     */
    public int getOrderCount() {
        return repository.count();
    }

    /**
     * 注文サマリーを標準出力に表示する。
     */
    public void printSummary() {
        System.out.println("===== 受注サマリー =====");
        System.out.printf("受注件数：%d件%n",getOrderCount());//「getOrderCounts」の「s」を削除
        System.out.printf("総売上金額：%,d円%n", calcGrandTotal());
        System.out.println("========================");
    }
}