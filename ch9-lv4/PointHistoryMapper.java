package com.example.point.repository;

import com.example.point.entity.PointHistoryEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PointHistoryMapper {

    @Select("SELECT id, member_id, point_change, reason, balance_after, created_at " +
            "FROM point_histories WHERE member_id = #{memberId} ORDER BY created_at DESC")
    List<PointHistoryEntity> findByMemberId(@Param("memberId") Integer memberId);

    @Insert("INSERT INTO point_histories (member_id, point_change, reason, balance_after, created_at) " +
            "VALUES (#{memberId}, #{pointChange}, #{reason}, #{balanceAfter}, #{createdAt})")
    void insert(PointHistoryEntity history);
}
