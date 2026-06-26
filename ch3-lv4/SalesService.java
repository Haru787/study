import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 売上集計サービスクラス。
 */
public class SalesService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SalesService.class);

    private final SalesRepository repository;

    public SalesService(SalesRepository repository) {
        this.repository = repository;
    }

    /**
     * 売上レコードを登録する。
     * 
     * @param record 登録する売上レコード
     */
    public void register(SalesRecord record) {
        repository.add(record);
    }

    /**
     * 担当者別売上合計を返す。
     * 
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
     * 週次売上レポートを印字する。
     */
    public void printWeeklySalesReport() {
        List<SalesRecord> all = repository.findAll();
        log.info("週次レポート出力開始: 件数={}", all.size());

        System.out.println("========== 週次売上レポート ==========");
        System.out.printf("%-6s  %-8s  %-10s  %-10s  %12s  %6s%n",
                "売上ID", "商品ID", "担当者", "カテゴリ", "金額（円）", "数量");
        System.out.println("-----------------------------------------------------------------");

        for (SalesRecord r : all) {
            // 担当者名が null の場合は "未登録" と表示する
            String repName = r.getSalesRepName() != null ? r.getSalesRepName().toUpperCase() : "不明";
            System.out.printf("%-6s  %-8s  %-10s  %-10s  %,12d  %6d個%n",
                    r.getSalesId(),
                    r.getProductId(),
                    repName,
                    r.getCategory(),
                    r.calcAmount(),
                    r.getQuantity());
        }
    }

    /**
     * トップセールス担当者を印字する。
     */
    public void printTopSalesRep() {
        Map<String, Integer> totals = aggregateByRep();
        String[] repNames = totals.keySet().toArray(new String[0]);

        System.out.println("\n========== トップセールス ==========");

        String topRep = null;
        int topTotal = 0;

        for (int i = 0; i < repNames.length; i++) {
            String rep = repNames[i];
            int amt = totals.get(rep);
            if (amt > topTotal) {
                topTotal = amt;
                topRep = rep;
            }
        }

        if (topRep != null) {
            System.out.printf("1位: %-10s  %,d円%n", topRep, topTotal);
        }
    }
}
