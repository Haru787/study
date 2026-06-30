package com.example.billing;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 請求書発行システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        InvoiceRepository  invoiceRepo  = new InvoiceRepository(null);
        ClientRepository   clientRepo   = new ClientRepository(null);
        InvoiceService     service      = new InvoiceService(invoiceRepo, clientRepo);

        service.registerClient(new Client("CLI-001", "株式会社テックワーク",   "東京都渋谷区1-2-3", "info@techwork.co.jp",   "03-1234-5678"));
        service.registerClient(new Client("CLI-002", "合同会社デジタルラボ",   "大阪府北区2-3-4",   "info@digitallab.co.jp", "06-2345-6789"));
        service.registerClient(new Client("CLI-003", "株式会社スマートオフィス", "愛知県名古屋市5-6-7", "info@smartoffice.co.jp","052-3456-7890"));

        Invoice inv1 = new Invoice("INV-2026-0001", "CLI-001", LocalDate.of(2026, 5, 25), LocalDate.of(2026, 6, 30));
        inv1.addDetail(new InvoiceDetail("D001", "INV-2026-0001", "Webシステム開発費",  1, 500000));
        inv1.addDetail(new InvoiceDetail("D002", "INV-2026-0001", "サーバー設定作業費", 1,  80000));
        inv1.addDetail(new InvoiceDetail("D003", "INV-2026-0001", "月次保守費",         1,  30000));

        Invoice inv2 = new Invoice("INV-2026-0002", "CLI-002", LocalDate.of(2026, 5, 25), LocalDate.of(2026, 6, 30));
        inv2.addDetail(new InvoiceDetail("D004", "INV-2026-0002", "コンサルティング料", 3, 150000));

        Invoice inv3 = new Invoice("INV-2026-0003", "CLI-003", LocalDate.of(2026, 5, 25), LocalDate.of(2026, 6, 30));
        inv3.addDetail(new InvoiceDetail("D005", "INV-2026-0003", "システム導入支援費", 1, 200000));
        inv3.addDetail(new InvoiceDetail("D006", "INV-2026-0003", "操作研修費（半日）", 2,  40000));

        service.issue(inv1);
        service.issue(inv2);
        service.issue(inv3);


        List<String> targetIds = Arrays.asList(
            "INV-2026-0001", "INV-2026-0002", "INV-2026-9999", "INV-2026-0003"
        );
        service.printInvoiceSummary(targetIds);
    }
}
