// package com.example.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 注文データの保存・検索を担うリポジトリクラス。
 */
public class OrderRepository {

    private final List<Order> store = new ArrayList<>();

    /** 注文を保存する。 */
    public void save(Order order) {
        store.add(order);
    }

    /**
     * 注文IDで注文を検索する。
     *
     * @param orderId 検索する注文ID
     * @return 見つかった注文、または null
     */
    public Order findById(String orderId) {
        for (Order order : store) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    /** 全注文を返す。 */
    public List<Order> findAll() {
        return store;
    }

    /** 保存件数を返す。 */
    public int count() {
        return store.size();
    }
}
