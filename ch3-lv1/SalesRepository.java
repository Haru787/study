// package com.example.sales;

import java.util.ArrayList;
import java.util.List;

/**
 * 売上レコードを管理するリポジトリクラス。
 */
public class SalesRepository {

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(SalesRepository.class);

    private final List<SalesRecord> records = new ArrayList<>();

    /**
     * 売上レコードを追加する。
     * @param record 追加する売上レコード
     */
    public void add(SalesRecord record) {
        records.add(record);
        log.info("売上レコード追加: salesId={}", record.getSalesId());
    }

    /**
     * 全レコードを返す。
     * @return 売上レコードのリスト（変更不可）
     */
    public List<SalesRecord> findAll() {
        return new ArrayList<>(records);
    }

    /**
     * 担当者名でレコードを絞り込む。
     * @param repName 担当者名
     * @return 一致するレコードのリスト
     */
    public List<SalesRecord> findBySalesRep(String repName) {
        List<SalesRecord> result = new ArrayList<>();
        for (SalesRecord r : records) {
            if (r.getSalesRepName().equals(repName)) {
                result.add(r);
            }
        }
        return result;
    }
}