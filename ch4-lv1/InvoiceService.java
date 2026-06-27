package com.example.billing;

import java.time.LocalDate;
import java.util.List;

/**
 * 請求書発行サービスクラス。
 */
public class InvoiceService {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository repository;

    /**
     * コンストラクタ。
     * @param repository 請求書リポジトリ
     */
    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    /**
     * 請求書を発行する。
     * @param invoice 発行する請求書
     */
    public void issue(Invoice invoice) {
        repository.insert(invoice);
        log.info("請求書発行: invoiceId={}, clientId={}",
            invoice.getInvoiceId(), invoice.getClientId());
    }

    /**
     * 請求書を送付済みに更新する。
     * @param invoiceId 対象の請求書 ID
     */
    public void markAsSent(String invoiceId) {
        repository.updateStatus(invoiceId, "SENT");
    }

    /**
     * 請求書の内容を標準出力に印字する。
     * @param invoice 印字対象の請求書
     * @param client  取引先情報
     */
    public void printInvoice(Invoice invoice, Client client) {
        System.out.println("========================================");
        System.out.println("              請求書");
        System.out.println("========================================");
        System.out.printf("請求書番号 : %s%n", invoice.getInvoiceId());
        System.out.printf("発行日     : %s%n", invoice.getIssueDate());
        System.out.printf("支払期限   : %s%n", invoice.getDueDate());
        System.out.printf("ステータス : %s%n", invoice.getStatus());
        System.out.println("----------------------------------------");
        System.out.printf("請求先     : %s%n", client.getClientName());
        System.out.printf("住所       : %s%n", client.getAddress());
        System.out.println("----------------------------------------");
        System.out.printf("%-22s  %6s  %10s  %12s%n",
            "摘要", "数量", "単価（円）", "小計（円）");
        System.out.println("----------------------------------------");

        for (InvoiceDetail d : invoice.getDetails()) {
            System.out.printf("%-22s  %6d  %,10d  %,12d%n",
                d.getDescription(), d.getQuantity(),
                d.getUnitPrice(), d.calcSubtotal());
        }

        System.out.println("----------------------------------------");
        System.out.printf("小計     : %,d円%n",            invoice.calcTotal());
        System.out.printf("消費税10%% : %,d円%n",           invoice.calcTotalWithTax() - invoice.calcTotal());
        System.out.printf("合計     : %,d円%n",             invoice.calcTotalWithTax());
        System.out.println("========================================");
    }
}