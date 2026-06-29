package com.example.billing;

import java.time.LocalDate;

/**
 * 請求書発行のコントローラクラス（Servlet ベース想定）。
 * ※ このクラスにコンパイルエラーが 1 箇所あります。
 */
public class InvoiceController {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    /**
     * 請求書発行リクエストを処理する。
     * @param invoiceId 請求書 ID
     * @param client    取引先
     * @param invoice   発行する請求書
     */
    public void handleIssue(String invoiceId, Client client, Invoice invoice) {
        log.info("請求書発行リクエスト受信: invoiceId={}", invoiceId);
        service.issue(invoice);
        service.printInvoice(invoice, client);
        log.info("請求書発行リクエスト完了: invoiceId={}", invoiceId);
    }

    /**
     * 請求書承認リクエストを処理する。
     * @param invoiceId 承認する請求書 ID
     * ★ コンパイルエラー2: approve() の戻り値を受け取る変数の型が Strnig（誤字）になっている
     */
    public void handleApprove(String invoiceId) {
        log.info("請求書承認リクエスト受信: invoiceId={}", invoiceId);
        Invoice approvedInvoice = service.approve(invoiceId);
        log.info("請求書承認完了: invoiceId={}", invoiceId);
    }
}