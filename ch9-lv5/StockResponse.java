package com.example.stock.dto.response;

import com.example.stock.entity.StockEntity;

public class StockResponse {

    private Integer id;
    private String warehouseCode;
    private String warehouseName;
    private String productCode;
    private String productName;
    private Integer quantity;
    private Integer reservedQuantity;

    public static StockResponse from(StockEntity entity) {
        StockResponse response = new StockResponse();
        response.id               = entity.getId();
        response.warehouseCode    = entity.getWarehouseCode();
        response.warehouseName    = entity.getWarehouseName();
        response.productCode      = entity.getProductCode();
        response.productName      = entity.getProductName();
        response.quantity         = entity.getQuantity();
        response.reservedQuantity = entity.getReservedQuantity();
        return response;
    }

    public Integer getId() { return id; }
    public String getWarehouseCode() { return warehouseCode; }
    public String getWarehouseName() { return warehouseName; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public Integer getReservedQuantity() { return reservedQuantity; }
}
