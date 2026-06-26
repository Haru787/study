// package com.example.sales;

/**
 * 売上レコードが見つからない場合にスローされる例外。
 */
public class SalesNotFoundException extends RuntimeException {

    private final String salesId;

    /**
     * @param salesId 検索対象の売上ID
     */
    public SalesNotFoundException(String salesId) {
        super("売上レコードが見つかりません: salesId=" + salesId);
        this.salesId = salesId;
    }

    /** @return 検索対象の売上ID */
    public String getSalesId() { return salesId; }
}
