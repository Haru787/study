// package com.example.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 注文ヘッダーを表すエンティティクラス。
 * 注文明細のリストを保持する。
 */
public class Order {

    private final String orderId;
    private final String customerId;
    private final String customerName;
    private final List<OrderItem> items;
    private String status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * @param orderId      注文ID
     * @param customerId   顧客ID
     * @param customerName 顧客名
     */
    public Order(String orderId, String customerId, String customerName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = "受付";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 注文明細を追加する。
     *
     * @param item 追加する明細
     */
    public void addItem(OrderItem item) {
        this.items.add(item);
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 注文合計金額を計算する（キャンセル済み明細は除外）。
     *
     * @return 合計金額
     */
    public int calcTotal() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.calcSubtotal();
        }
        return total;
    }

    /** @return 注文ID */
    public String getOrderId() { return orderId; }
    /** @return 顧客ID */
    public String getCustomerId() { return customerId; }
    /** @return 顧客名 */
    public String getCustomerName() { return customerName; }
    /** @return 明細リスト */
    public List<OrderItem> getItems() { return items; }
    /** @return ステータス */
    public String getStatus() { return status; }
    /** @return 作成日時 */
    public LocalDateTime getCreatedAt() { return createdAt; }
}