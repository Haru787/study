package com.example.point.repository;

import com.example.point.entity.MemberEntity;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("SELECT id, member_code, name, email, phone, point_balance, rank, " +
            "joined_at, created_at, updated_at FROM members WHERE id = #{id}")
    Optional<MemberEntity> findById(@Param("id") Integer id);

    @Update("UPDATE members SET point_balance = #{pointBalance}, updated_at = NOW() WHERE id = #{id}")
    void updatePointBalance(@Param("id") Integer id, @Param("pointBalance") Integer pointBalance);
}
