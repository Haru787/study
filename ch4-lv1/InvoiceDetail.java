package com.example.billing;

import java.time.LocalDateTime;

/**
 * 請求明細エンティティ。1 件の請求書に複数紐付く。
 */
public class InvoiceDetail {

    private String        detailId;
    private String        invoiceId;
    private String        description;
    private int           quantity;
    private int           unitPrice;
    private LocalDateTime createdAt;

    /**
     * 請求明細コンストラクタ。
     */
    public InvoiceDetail(String detailId, String invoiceId,
                         String description, int quantity, int unitPrice) {
        this.detailId    = detailId;
        this.invoiceId   = invoiceId;
        this.description = description;
        this.quantity    = quantity;
        this.unitPrice   = unitPrice;
        this.createdAt   = LocalDateTime.now();
    }

    /** 明細小計（数量 × 単価）を返す。 */
    public int calcSubtotal() { return quantity * unitPrice; }

    public String getDetailId()    { return detailId; }
    public String getInvoiceId()   { return invoiceId; }
    public String getDescription() { return description; }
    public int    getQuantity()    { return quantity; }
    public int    getUnitPrice()   { return unitPrice; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}