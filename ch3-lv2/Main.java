import java.time.LocalDateTime;

/**
 * 販売管理システム エントリーポイント。
 */
public class Main {

    public static void main(String[] args) {

        SalesRepository repo    = new SalesRepository();
        SalesService    service = new SalesService(repo);

        LocalDateTime base = LocalDateTime.of(2026, 5, 25, 9, 0);

        service.register(new SalesRecord("S001", "PRD-101", "PC",       "田中 一郎",  5, 12800, base.plusHours(1)));
        service.register(new SalesRecord("S002", "PRD-201", "周辺機器", "鈴木 花子",  3, 54000, base.plusHours(2)));
        service.register(new SalesRecord("S003", "PRD-301", "消耗品",   "田中 一郎",  8,  3200, base.plusHours(3)));
        service.register(new SalesRecord("S004", "PRD-101", "PC",       "高橋 三郎",  2, 12800, base.plusHours(4)));
        service.register(new SalesRecord("S005", "PRD-202", "周辺機器", "鈴木 花子", 10,  1500, base.plusHours(5)));

        service.printRepSummary();
        System.out.println();
        service.printCategorySummary();
    }
}