package com.example.stock.entity;

import java.time.LocalDateTime;

public class StockTransferHistoryEntity {

    private Integer id;
    private String transferNo;
    private String fromWarehouseCode;
    private String toWarehouseCode;
    private String productCode;
    private Integer quantity;
    private String status;
    private String executedBy;
    private LocalDateTime executedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTransferNo() { return transferNo; }
    public void setTransferNo(String transferNo) { this.transferNo = transferNo; }

    public String getFromWarehouseCode() { return fromWarehouseCode; }
    public void setFromWarehouseCode(String fromWarehouseCode) { this.fromWarehouseCode = fromWarehouseCode; }

    public String getToWarehouseCode() { return toWarehouseCode; }
    public void setToWarehouseCode(String toWarehouseCode) { this.toWarehouseCode = toWarehouseCode; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getExecutedBy() { return executedBy; }
    public void setExecutedBy(String executedBy) { this.executedBy = executedBy; }

    public LocalDateTime getExecutedAt() { return executedAt; }
    public void setExecutedAt(LocalDateTime executedAt) { this.executedAt = executedAt; }
}
