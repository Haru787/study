package com.example.employee;

/**
 * 役職の振る舞いを定義するインターフェース。
 */
public interface Position {

    /**
     * 役職名を返す。
     *
     * @return 役職名
     */
    String getPositionName();

    /**
     * 役職手当（月額）を返す。
     *
     * @return 役職手当（円）
     */
    int getAllowance();
}