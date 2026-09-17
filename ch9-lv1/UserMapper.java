package com.example.user.repository;

import com.example.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, email, full_name, department, role, is_active, " +
            "created_at, updated_at FROM users WHERE id = #{id}")
    Optional<UserEntity> findById(@Param("id") Integer id);

    @Select("SELECT id, username, email, full_name, department, role, is_active, " +
            "created_at, updated_at FROM users")
    List<UserEntity> findAll();

    @Select("SELECT id, username, email, full_name, department, role, is_active, " +
            "created_at, updated_at FROM users WHERE department = #{department}")
    List<UserEntity> findByDepartment(@Param("department") String department);
}
