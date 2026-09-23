package com.example.stock.exception;

public class StockNotFoundException extends RuntimeException {

    private final String warehouseCode;
    private final String productCode;

    public StockNotFoundException(String warehouseCode, String productCode) {
        super("在庫が見つかりません。倉庫コード: " + warehouseCode + ", 商品コード: " + productCode);
        this.warehouseCode = warehouseCode;
        this.productCode   = productCode;
    }

    public String getWarehouseCode() { return warehouseCode; }
    public String getProductCode() { return productCode; }
}
