package com.example.stock.dto.response;

import com.example.stock.entity.StockTransferHistoryEntity;

import java.time.LocalDateTime;

public class TransferHistoryResponse {

    private Integer id;
    private String transferNo;
    private String fromWarehouseCode;
    private String toWarehouseCode;
    private String productCode;
    private Integer quantity;
    private String status;
    private String executedBy;
    private LocalDateTime executedAt;

    public static TransferHistoryResponse from(StockTransferHistoryEntity entity) {
        TransferHistoryResponse response = new TransferHistoryResponse();
        response.id                = entity.getId();
        response.transferNo        = entity.getTransferNo();
        response.fromWarehouseCode = entity.getFromWarehouseCode();
        response.toWarehouseCode   = entity.getToWarehouseCode();
        response.productCode       = entity.getProductCode();
        response.quantity          = entity.getQuantity();
        response.status            = entity.getStatus();
        response.executedBy        = entity.getExecutedBy();
        response.executedAt        = entity.getExecutedAt();
        return response;
    }

    public Integer getId() { return id; }
    public String getTransferNo() { return transferNo; }
    public String getFromWarehouseCode() { return fromWarehouseCode; }
    public String getToWarehouseCode() { return toWarehouseCode; }
    public String getProductCode() { return productCode; }
    public Integer getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public String getExecutedBy() { return executedBy; }
    public LocalDateTime getExecutedAt() { return executedAt; }
}
