// package com.example.order;

import java.util.List;

/**
 * 受発注業務ロジックを集約するサービスクラス。
 */
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /** 注文を受付登録する。 */
    public void accept(Order order) {
        order.setStatus("受付");
        repository.save(order);
    }

    /** 注文を出荷済みに更新する。 */
    public void ship(String orderId) {
        Order order = repository.findById(orderId);
        if (order == null) {
            throw new InvalidOrderException(orderId, "注文が見つかりません");
        }
        order.setStatus("出荷済");
    }

    /** 全注文の総売上金額を集計する。 */
    public int calcGrandTotal() {
        int total = 0;
        for (Order order : repository.findAll()) {
            total += order.calcTotal();
        }
        return total;
    }

    /** 登録件数を返す。 */
    public int getOrderCount() {
        return repository.count();
    }

    /**
     * 全注文の明細を一覧表示する。
     */
    public void printAllItems() {//戻り値Stringをvoidに変更
        System.out.println("===== 受注明細レポート =====");
        for (Order order : repository.findAll()) {
            for (OrderItem item : order.getItems()) {
                System.out.printf("[%s] %-24s  数量：%d  単価：%,6d円%n",
                        order.getOrderId(),
                        item.getProductName(),
                        item.getQuantity(),
                        item.getUnitPrice()
                );
            }
        }
    }

    /**
     * 顧客別の合計金額を配列で集計し、表示する。
     */
    public void printCustomerSummary() {
        List<Order> orders = repository.findAll();
        String[] customerNames = new String[orders.size()];
        int[] totals = new int[orders.size()];

        for (int i = 0; i < orders.size(); i++) {
            customerNames[i] = orders.get(i).getCustomerName();
            totals[i] = orders.get(i).calcTotal();//calcTotal("TAX_EXCLUDED");の"TAX_EXCLUDED"を削除
        }

        System.out.println("===== 顧客別集計 =====");
        for (int i = 0; i < totals.length; i++) {// <=を<に修正
            System.out.printf("%-24s  %,d円%n", customerNames[i], totals[i]);
        }
    }

    /**
     * 全注文サマリーを表示する。
     *   アクセス修飾子の付け間違いがある
     */
    protected int calcGrandTotalInternal() {
        return calcGrandTotal();
    }
}
