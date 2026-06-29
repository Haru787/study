package com.example.billing;

import java.time.LocalDate;
import java.util.List;

/**
 * 請求書発行サービスクラス。
 * ※ このクラスにコンパイルエラーが 2 箇所あります。
 */
public class InvoiceService {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    /**
     * 請求書を発行する。
     * @param invoice 発行する請求書
     */
    public void issue(Invoice invoice) {
        repository.insert(invoice);
        log.info("請求書発行: invoiceId={}", invoice.getInvoiceId());
    }

    /**
     * 請求書を承認済みに更新する。
     * @param invoiceId 対象の請求書 ID
     * @return 更新後の請求書
     * ★ コンパイルエラー1: 戻り値が void なのに return 文がある（戻り値型を Invoice に修正すべき）
     */
    public Invoice approve(String invoiceId) {
        Invoice invoice = repository.findById(invoiceId);
        if (invoice == null) {
            throw new InvoiceNotFoundException(invoiceId);
        }
        repository.updateStatus(invoiceId, "APPROVED");
        invoice.setStatus("APPROVED");
        log.info("請求書承認: invoiceId={}", invoiceId);
        return invoice;
    }

    /**
     * 請求書を印字する。
     * @param invoice 対象の請求書
     * @param client  取引先
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

        for (InvoiceDetail d : invoice.getDetails()) {
            System.out.printf("  %-22s  %4d個  %,10d円  %,12d円%n",
                d.getDescription(), d.getQuantity(),
                d.getUnitPrice(), d.calcSubtotal());
        }

        System.out.println("----------------------------------------");
        System.out.printf("小計     : %,d円%n",   invoice.calcTotal());
        System.out.printf("消費税10%% : %,d円%n",  invoice.calcTotalWithTax() - invoice.calcTotal());
        System.out.printf("合計     : %,d円%n",   invoice.calcTotalWithTax());
        System.out.println("========================================");
    }
}