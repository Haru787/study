import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 売上集計サービスクラス。
 * ※ このクラスにコンパイルエラーが 2 箇所あります。
 */
public class SalesService {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(SalesService.class);

    private final SalesRepository repository;

    /**
     * コンストラクタ。
     * @param repository 売上リポジトリ
     */
    public SalesService(SalesRepository repository) {
        this.repository = repository;
    }

    /**
     * 担当者別売上合計を計算して返す。
     * @return 担当者名 → 売上合計のマップ
     */
    public Map<String, Integer> aggregateByRep() {
        Map<String, Integer> result = new HashMap<>();
        for (SalesRecord r : repository.findAll()) {
            result.put(r.getSalesRepName(),
                result.getOrDefault(r.getSalesRepName(), 0) + r.calcAmount());
        }
        return result;
    }

    /**
     * カテゴリ別売上合計を計算して返す。
     * @return カテゴリ名 → 売上合計のマップ
     */
    public Map<String, Integer> aggregateByCategory() {
        Map<String, Integer> result = new HashMap<>();
        for (SalesRecord r : repository.findAll()) {
            result.put(r.getCategory(),
                result.getOrDefault(r.getCategory(), 0) + r.calcAmount());
        }
        return result;
    }

    /**
     * 担当者別集計レポートを印字する。
     */
    public void printRepSummary() {
        Map<String, Integer> totals = aggregateByRep();
        log.info("担当者別集計出力: {} 名分", totals.size());
        System.out.println("========== 担当者別売上集計 ==========");
        for (Map.Entry<String, Integer> e : totals.entrySet()) {
            System.out.printf("  %-12s  %,10d円%n", e.getKey(), e.getValue());
        }
    }

    /**
     * カテゴリ別集計レポートを印字する。
     */
    public void printCategorySummary() {
        Map<String, Integer> totals = aggregateByCategory();
        System.out.println("========== カテゴリ別売上集計 ==========");
        for (Map.Entry<String, Integer> e : totals.entrySet()) {
            System.out.printf("  %-16s  %,10d円%n", e.getKey(), e.getValue());
        }
    }

    /**
     * 売上レコードを登録する。数量・単価のバリデーションを行う。
     * @param record 登録する売上レコード
     * @throws InvalidSalesDataException 数量または単価が 0 以下の場合
     */
    void register(SalesRecord record) {     // ★ アクセス修飾子が不足している（publicにすべき）
        if (record.getQuantity() <= 0) {
            throw new InvalidSalesDataException("quantity", record.getQuantity());
        }
        if (record.getUnitPrice() <= 0) {
            throw new InvalidSalesDataException("unitPrice", record.getUnitPrice());
        }
        repository.add(record);
        log.info("売上登録完了: salesId={}", record.getSalesId());
    }
}