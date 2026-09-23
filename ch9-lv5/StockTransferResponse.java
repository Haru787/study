package com.example.stock.dto.response;

public class StockTransferResponse {

    private String transferNo;
    private String fromWarehouseCode;
    private String toWarehouseCode;
    private String productCode;
    private Integer quantity;
    private String status;
    private Integer fromBalanceAfter;
    private Integer toBalanceAfter;

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

    public Integer getFromBalanceAfter() { return fromBalanceAfter; }
    public void setFromBalanceAfter(Integer fromBalanceAfter) { this.fromBalanceAfter = fromBalanceAfter; }

    public Integer getToBalanceAfter() { return toBalanceAfter; }
    public void setToBalanceAfter(Integer toBalanceAfter) { this.toBalanceAfter = toBalanceAfter; }
}
