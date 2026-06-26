// package com.example.sales;

import java.time.LocalDateTime;

/**
 * 1件の売上レコードを表すエンティティクラス。
 */
public class SalesRecord {

    private String   salesId;
    private String   productId;
    private String   salesRepName;
    private int      quantity;
    private int      unitPrice;
    private String   channel;
    private LocalDateTime soldAt;
    private LocalDateTime createdAt;

    /**
     * 売上レコードコンストラクタ。
     */
    public SalesRecord(String salesId, String productId, String salesRepName,
                       int quantity, int unitPrice, String channel, LocalDateTime soldAt) {
        this.salesId      = salesId;
        this.productId    = productId;
        this.salesRepName = salesRepName;
        this.quantity     = quantity;
        this.unitPrice    = unitPrice;
        this.channel      = channel;
        this.soldAt       = soldAt;
        this.createdAt    = LocalDateTime.now();
    }

    /** 売上金額（数量 × 単価）を返す。 */
    public int calcAmount() { return quantity * unitPrice; }

    public String   getSalesId()      { return salesId; }
    public String   getProductId()    { return productId; }
    public String   getSalesRepName() { return salesRepName; }
    public int      getQuantity()     { return quantity; }
    public int      getUnitPrice()    { return unitPrice; }
    public String   getChannel()      { return channel; }
    public LocalDateTime getSoldAt()  { return soldAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}