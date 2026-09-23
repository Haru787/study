package com.example.stock.repository;

import com.example.stock.entity.StockEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StockMapper {

    @Select("SELECT id, warehouse_code, warehouse_name, product_code, product_name, " +
            "quantity, reserved_quantity, last_updated_by, created_at, updated_at " +
            "FROM stocks WHERE warehouse_code = #{warehouseCode}")
    List<StockEntity> findByWarehouse(@Param("warehouseCode") String warehouseCode);

    @Select("SELECT id, warehouse_code, warehouse_name, product_code, product_name, " +
            "quantity, reserved_quantity, last_updated_by, created_at, updated_at " +
            "FROM stocks WHERE product_code = #{productCode}")
    List<StockEntity> findByProduct(@Param("productCode") String productCode);

    @Select("SELECT id, warehouse_code, warehouse_name, product_code, product_name, " +
            "quantity, reserved_quantity, last_updated_by, created_at, updated_at " +
            "FROM stocks WHERE warehouse_code = #{warehouseCode} AND product_code = #{productCode}")
    Optional<StockEntity> findByWarehouseAndProduct(
            @Param("warehouseCode") String warehouseCode,
            @Param("productCode") String productCode);

    @Update("UPDATE stocks SET quantity = #{quantity}, last_updated_by = #{lastUpdatedBy}, " +
            "updated_at = NOW() WHERE warehouse_code = #{warehouseCode} AND product_code = #{productCode}")
    void updateQuantity(@Param("warehouseCode") String warehouseCode,
                        @Param("productCode") String productCode,
                        @Param("quantity") Integer quantity,
                        @Param("lastUpdatedBy") String lastUpdatedBy);
}
