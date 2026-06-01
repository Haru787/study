// package com.example.order;

import java.time.LocalDateTime;

/**
 * 注文明細を表すエンティティクラス。
 * 1件の注文に含まれる商品1行分の情報を保持する。
 */
public class OrderItem {

    private final String itemId;
    private final String orderId;
    private final String productCode;
    private final String productName;
    private int quantity;
    private final int unitPrice;
    private boolean cancelled;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * @param itemId      明細ID
     * @param orderId     注文ID
     * @param productCode 商品コード
     * @param productName 商品名
     * @param quantity    数量
     * @param unitPrice   単価
     */
    public OrderItem(String itemId, String orderId, String productCode,
                     String productName, int quantity, int unitPrice) {
        if (quantity <= 0) {
            throw new InvalidOrderException(orderId, "数量は1以上を指定してください: " + quantity);
        }
        if (unitPrice < 0) {
            throw new InvalidOrderException(orderId, "単価は0以上を指定してください: " + unitPrice);
        }
        this.itemId = itemId;
        this.orderId = orderId;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.cancelled = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /** @return 明細ID */
    public String getItemId() { return itemId; }
    /** @return 注文ID */
    public String getOrderId() { return orderId; }
    /** @return 商品コード */
    public String getProductCode() { return productCode; }
    /** @return 商品名 */
    public String getProductName() { return productName; }
    /** @return 数量 */
    public int getQuantity() { return quantity; }
    /** @return 単価 */
    public int getUnitPrice() { return unitPrice; }
    /** @return キャンセル済みかどうか */
    public boolean isCancelled() { return cancelled; }
    /** @return 作成日時 */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /** @return 更新日時 */
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    /**
     * 小計金額を返す（キャンセル済みの場合は0）。
     *
     * @return 小計金額
     */
    public int calcSubtotal() {
        if (cancelled) return 0;
        return quantity * unitPrice;
    }

    /**
     * 明細をキャンセル状態にする。
     */
    public void cancel() {
        this.cancelled = true;
        this.updatedAt = LocalDateTime.now();
    }
}