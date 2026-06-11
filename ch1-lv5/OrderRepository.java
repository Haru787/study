// package com.example.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 注文データの保存・検索を担うリポジトリクラス。
 */
public class OrderRepository {

    private final List<Order> store = new ArrayList<>();

    public void save(Order order) {
        store.add(order);
    }

    public Order findById(String orderId) {
        for (Order order : store) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> findAll() {
        return store;
    }

    public int count() {
        return store.size();
    }
}
