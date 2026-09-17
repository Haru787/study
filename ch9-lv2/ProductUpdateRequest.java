package com.example.product.dto.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductUpdateRequest {

    @NotBlank(message = "商品名は必須です")
    private String productName;

    @NotBlank(message = "カテゴリは必須です")
    private String category;

    @NotNull(message = "単価は必須です")
    @DecimalMin(value = "0.0", inclusive = false, message = "単価は0より大きい値を入力してください")
    private BigDecimal unitPrice;

    @Min(value = 0, message = "在庫数は0以上で入力してください")
    private Integer stockQuantity;

    private String description;

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
