package com.example.billing;

import java.time.LocalDate;

/**
 * 請求書発行システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        InvoiceRepository  repository = new InvoiceRepository(null);
        InvoiceBatchService service   = new InvoiceBatchService(repository);

        service.registerClient(new Client("CLI-001", "株式会社テックワーク",        "東京都渋谷区1-2-3", "info@tw.co.jp",    "03-1234-5678"));
        service.registerClient(new Client("CLI-002", "合同会社デジタルラボ",        "大阪府北区2-3-4",   "info@dl.co.jp",    "06-2345-6789"));

        service.registerClient(new Client("CLI-004", "株式会社リードソリューション", "福岡市博多区7-8-9", "info@lead.co.jp",  "092-3456-7890"));

        LocalDate issueDate = LocalDate.of(2026, 5, 25);

        Invoice inv1 = new Invoice("INV-2026-0001", "CLI-001", issueDate, LocalDate.of(2026, 6, 30));
        inv1.addDetail(new InvoiceDetail("D001", "INV-2026-0001", "Webシステム開発費",  1, 500000));
        inv1.addDetail(new InvoiceDetail("D002", "INV-2026-0001", "サーバー設定作業費", 1,  80000));
        inv1.addDetail(new InvoiceDetail("D003", "INV-2026-0001", "月次保守費",         1,  30000));

        Invoice inv2 = new Invoice("INV-2026-0002", "CLI-002", issueDate, LocalDate.of(2026, 7, 15));
        inv2.addDetail(new InvoiceDetail("D004", "INV-2026-0002", "コンサルティング料", 3, 150000));

        Invoice inv3 = new Invoice("INV-2026-0003", "CLI-003", issueDate, LocalDate.of(2026, 5, 20));
        inv3.addDetail(new InvoiceDetail("D005", "INV-2026-0003", "システム導入支援費", 1, 200000));
        inv3.addDetail(new InvoiceDetail("D006", "INV-2026-0003", "操作研修費（半日）", 2,  40000));

        Invoice inv4 = new Invoice("INV-2026-0004", "CLI-004", issueDate, LocalDate.of(2026, 6, 20));
        inv4.addDetail(new InvoiceDetail("D007", "INV-2026-0004", "保守サポート費",     1, 100000));

        service.addInvoice(inv1);
        service.addInvoice(inv2);
        service.addInvoice(inv3);
        service.addInvoice(inv4);

        service.batchInsert();

        LocalDate today = LocalDate.of(2026, 5, 25);
        service.printUnpaidInvoices(today);
        service.printOverdueInvoices(today);

        System.out.printf("%n全体合計（税込）: %,d円%n", service.calcGrandTotal());
    }
}