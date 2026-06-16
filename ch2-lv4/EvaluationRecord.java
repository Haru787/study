// package com.example.employee;

import java.time.LocalDateTime;

/**
 * 評価スコアレコードを表すクラス。
 */
public class EvaluationRecord {

    private final String employeeId;
    private final String period;
    private final int score;
    private final LocalDateTime evaluatedAt;

    /**
     * @param employeeId 従業員ID
     * @param period     評価期間（例: 2026-Q1）
     * @param score      スコア（0〜100）
     */
    public EvaluationRecord(String employeeId, String period, int score) {
        this.employeeId  = employeeId;
        this.period      = period;
        this.score       = score;
        this.evaluatedAt = LocalDateTime.now();
    }

    public String getEmployeeId() { return employeeId; }
    public String getPeriod()     { return period; }
    public int getScore()         { return score; }
    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }

    /**
     * スコアに基づく評価ランクを返す。
     *
     * @return S / A / B / C
     */
    public String getRank() {
        if (score >= 90) return "S";
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        return "C";
    }
}
