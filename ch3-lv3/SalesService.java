import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 売上集計サービスクラス。
 */
public class SalesService {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(SalesService.class);

    private final SalesRepository repository;

    public SalesService(SalesRepository repository) {
        this.repository = repository;
    }

    /**
     * 売上レコードを登録する。
     * @param record 登録する売上レコード
     */
    public void register(SalesRecord record) {
        repository.add(record);
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
     * 月次売上レポートを印字する。
     * 売上レコードの一覧と担当者別合計を出力する。
     */
    public void printMonthlySalesReport() {
        List<SalesRecord> all = repository.findAll();
        log.info("月次レポート出力開始: 件数={}", all.size());

        System.out.println("========== 月次売上レポート ==========");
        System.out.printf("%-6s  %-8s  %-10s  %-10s  %12s  %6s%n",
            "売上ID", "商品ID", "担当者", "カテゴリ", "金額（円）", "数量");
        System.out.println("-----------------------------------------------------------------");

        for (SalesRecord r : all) {
            System.out.printf("%-6s  %-8s  %-10s  %-10s  %,12d  %6d個%n",
                r.getSalesId(),
                r.getProductId(),
                r.getSalesRepName(),
                r.getCategory(),
                r.calcAmount(),
                r.getQuantity()
            );
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("【担当者別合計】");
        Map<String, Integer> totals = aggregateByRep();
        for (Map.Entry<String, Integer> e : totals.entrySet()) {
            System.out.printf("  %-10s  %,12d円%n", e.getKey(), e.getValue());
        }

        int grandTotal = totals.values().stream().mapToInt(Integer::intValue).sum();
        System.out.printf("%n合計売上：%,d円%n", grandTotal);
    }
}
