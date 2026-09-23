package com.example.order.repository;

import com.example.order.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    @Select("SELECT id, order_no, customer_id, total_amount, tax_amount, status, " +
            "shipping_address, payment_method, ordered_at, created_at, updated_at " +
            "FROM orders WHERE id = #{id}")
    Optional<OrderEntity> findById(@Param("id") Integer id);

    @Select("SELECT id, order_no, customer_id, total_amount, tax_amount, status, " +
            "shipping_address, payment_method, ordered_at, created_at, updated_at " +
            "FROM orders WHERE customer_id = #{customerId}")
    List<OrderEntity> findByCustomer(@Param("customerId") Integer customerId);

    @Update("UPDATE orders SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);
}
