package com.example.billing;

import java.time.LocalDate;

/**
 * 請求書発行システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        InvoiceRepository  repository  = new InvoiceRepository(null);
        InvoiceService     service     = new InvoiceService(repository);
        InvoiceController  controller  = new InvoiceController(service);

        Client client = new Client(
            "CLI-001", "株式会社テックワーク",
            "東京都渋谷区1-2-3 テックビル5F",
            "info@techwork.co.jp", "03-1234-5678"
        );

        Invoice invoice = new Invoice(
            "INV-2026-0001", "CLI-001",
            LocalDate.of(2026, 5, 25),
            LocalDate.of(2026, 6, 30)
        );
        invoice.addDetail(new InvoiceDetail("D001", "INV-2026-0001", "Webシステム開発費",  1, 500000));
        invoice.addDetail(new InvoiceDetail("D002", "INV-2026-0001", "サーバー設定作業費", 1,  80000));
        invoice.addDetail(new InvoiceDetail("D003", "INV-2026-0001", "月次保守費",         1,  30000));

        controller.handleIssue("INV-2026-0001", client, invoice);
    }
}