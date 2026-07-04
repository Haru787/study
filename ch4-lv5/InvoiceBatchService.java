package com.example.billing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 請求書バッチ処理サービスクラス。
 *    コンパイルエラーをすべて修正した後に実行すると、実行時例外が 1 箇所発生します。
 */
public class InvoiceBatchService {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(InvoiceBatchService.class);

    private final InvoiceRepository repository;

    /** インメモリキャッシュ（invoiceId → Invoice） */
    private final Map<String, Invoice> cache = new HashMap<>();
    /** インメモリキャッシュ（clientId → Client） */
    private final Map<String, Client>  clientCache = new HashMap<>();

    public InvoiceBatchService(InvoiceRepository repository) {
        this.repository = repository;
    }

    /**
     * 取引先をキャッシュに登録する。
     * @param client 登録する取引先
     */
    public void registerClient(Client client) {
        clientCache.put(client.getClientId(), client);
    }

    /**
     * 請求書をキャッシュに追加する。
     * @param invoice 追加する請求書
     */
    public void addInvoice(Invoice invoice) {
        cache.put(invoice.getInvoiceId(), invoice);
    }

    /**
     * キャッシュ内の請求書を一括 INSERT する。
     * @return 登録件数
     */
    public int batchInsert() {
        log.info("[BATCH] 請求書バッチ登録開始: {} 件", cache.size());
        int count = 0;
        for (Invoice inv : cache.values()) {
            repository.insert(inv);
            count++;
        }
        log.info("[BATCH] バッチ登録完了");
        return count;
    }

    /**
     * 未払い（DRAFT）の請求書一覧を印字する。
     * @param baseDate 基準日（この日以降が支払期限の請求書を対象とする）
     */
    public void printUnpaidInvoices(LocalDate baseDate) {
        System.out.println("========== 未払い請求書一覧 ==========");
        for (Invoice inv : cache.values()) {
            if ("DRAFT".equals(inv.getStatus()) && !inv.getDueDate().isBefore(baseDate)) {
                Client client = clientCache.get(inv.getClientId());
                String name   = client.getClientName();
                System.out.printf("%-16s  %-24s  %-12s  %,12d円%n",
                    inv.getInvoiceId(), name,
                    inv.getDueDate(), inv.calcTotalWithTax());
            }
        }
    }

    /**
     * 期限超過（支払期限が基準日より前の DRAFT）請求書一覧を印字する。
     * @param baseDate 基準日
     */
    public void printOverdueInvoices(LocalDate baseDate) {
        System.out.println("\n========== 期限超過請求書 ==========");
        for (Invoice inv : cache.values()) {
            if ("DRAFT".equals(inv.getStatus()) && inv.getDueDate().isBefore(baseDate)) {
                Client client    = clientCache.get(inv.getClientId());
                String clientName = client != null ? client.getClientName() : "不明";
                System.out.printf("%-16s  %-24s  %-12s  %,12d円  【要督促】%n",
                    inv.getInvoiceId(), clientName,
                    inv.getDueDate(), inv.calcTotalWithTax());
            }
        }
    }

    /**
     * 全請求書の税込合計を返す。
     * @return 税込合計金額（円）
     */
    public int calcGrandTotal() {
        int total = 0;
        for (Invoice inv : cache.values()) {
            total += inv.calcTotalWithTax();
        }
        return total;
    }

    /**
     * 取引先別の請求合計を返す。
     * @return 取引先 ID → 税込合計のマップ
     */
    public Map<String, Integer> aggregateByClient() {
        Map<String, Integer> result = new HashMap<>();
        for (Invoice inv : cache.values()) {
            result.put(inv.getClientId(),
                result.getOrDefault(inv.getClientId(), 0) + inv.calcTotalWithTax());
        }
        return result;
    }
}