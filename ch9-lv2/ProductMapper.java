package com.example.product.repository;

import com.example.product.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    @Select("SELECT id, product_code, product_name, category, unit_price, " +
            "stock_quantity, supplier_id, description, is_active, created_at, updated_at " +
            "FROM products WHERE id = #{id}")
    Optional<ProductEntity> findById(@Param("id") Integer id);

    @Select("SELECT id, product_code, product_name, category, unit_price, " +
            "stock_quantity, supplier_id, description, is_active, created_at, updated_at " +
            "FROM products")
    List<ProductEntity> findAll();

    @Select("SELECT id, product_code, product_name, category, unit_price, " +
            "stock_quantity, supplier_id, description, is_active, created_at, updated_at " +
            "FROM products WHERE category = #{category}")
    List<ProductEntity> findByCategory(@Param("category") String category);

    @Select("SELECT COUNT(*) FROM products WHERE product_code = #{productCode}")
    int countByProductCode(@Param("productCode") String productCode);

    @Insert("INSERT INTO products (product_code, product_name, category, unit_price, " +
            "stock_quantity, supplier_id, description, is_active, created_at, updated_at) " +
            "VALUES (#{productCode}, #{productName}, #{category}, #{unitPrice}, " +
            "#{stockQuantity}, #{supplierId}, #{description}, #{isActive}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ProductEntity product);

    @Update("UPDATE products SET product_name = #{productName}, category = #{category}, " +
            "unit_price = #{unitPrice}, stock_quantity = #{stockQuantity}, " +
            "description = #{description}, updated_at = #{updatedAt} WHERE id = #{id}")
    void update(ProductEntity product);

    @Update("UPDATE products SET is_active = false, updated_at = NOW() WHERE id = #{id}")
    void deactivate(@Param("id") Integer id);
}
