package com.example.billing;

/**
 * 請求書が見つからない場合にスローされる例外。
 */
public class InvoiceNotFoundException extends RuntimeException {

    private final String invoiceId;

    public InvoiceNotFoundException(String invoiceId) {
        super("請求書が見つかりません: invoiceId=" + invoiceId);
        this.invoiceId = invoiceId;
    }

    public String getInvoiceId() { return invoiceId; }
}