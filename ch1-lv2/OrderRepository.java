// package com.example.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 注文データの保存・検索を担うリポジトリクラス。
 */
public class OrderRepository {

    private List<Order> store = new ArrayList<>();

    /**
     * 注文を保存する。
     *
     * @param order 保存する注文
     */
    public void save(Order order) {
        if (order == null) {
            throw new InvalidOrderException("NULL", "注文オブジェクトがnullです");
        }
        store.add(order);
    }

    /**
     * 注文IDで注文を検索する。見つからない場合は null を返す。
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

    /**
     * 顧客IDで注文リストを検索する。
     *
     * @param customerId 検索する顧客ID
     * @return 該当注文リスト
     */
    public List<Order> findByCustomerId(String customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : store) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * 保存済みの全注文を返す。
     *
     * @return 全注文リスト
     */
    public List<Order> findAll() {
        return store;
    }

    /**
     * 保存件数を返す。
     *
     * @return 件数
     */
    public int count() {//「String」を「int」に修正
        return store.size();
    }
}