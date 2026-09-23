package com.example.stock.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StockTransferRequest {

    @NotBlank(message = "移動元倉庫コードは必須です")
    private String fromWarehouseCode;

    @NotBlank(message = "移動先倉庫コードは必須です")
    private String toWarehouseCode;

    @NotBlank(message = "商品コードは必須です")
    private String productCode;

    @NotNull(message = "移動数量は必須です")
    @Min(value = 1, message = "移動数量は1以上で入力してください")
    private Integer quantity;

    @NotBlank(message = "実行者は必須です")
    private String executedBy;

    public String getFromWarehouseCode() { return fromWarehouseCode; }
    public void setFromWarehouseCode(String fromWarehouseCode) { this.fromWarehouseCode = fromWarehouseCode; }

    public String getToWarehouseCode() { return toWarehouseCode; }
    public void setToWarehouseCode(String toWarehouseCode) { this.toWarehouseCode = toWarehouseCode; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getExecutedBy() { return executedBy; }
    public void setExecutedBy(String executedBy) { this.executedBy = executedBy; }
}
