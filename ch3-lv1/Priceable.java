// package com.example.sales;

/**
 * 価格を持つオブジェクトを表すインターフェース。
 */
public interface Priceable {
    /**
     * 単価を返す。
     * @return 単価（円）
     */
    int getUnitPrice();

    /**
     * 合計金額を返す。
     * @return 合計金額（円）
     */
    int calcTotalPrice();
}
