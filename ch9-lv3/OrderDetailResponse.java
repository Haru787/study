package com.example.order.dto.response;

import com.example.order.entity.OrderDetailEntity;

import java.math.BigDecimal;

public class OrderDetailResponse {

    private Integer id;
    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;

    public static OrderDetailResponse from(OrderDetailEntity entity) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.id          = entity.getId();
        response.productId   = entity.getProductId();
        response.productName = entity.getProductName();
        response.unitPrice   = entity.getUnitPrice();
        response.quantity    = entity.getQuantity();
        response.subtotal    = entity.getSubtotal();
        return response;
    }

    public Integer getId() { return id; }
    public Integer getProductId() { return productId; }
    public String getProductName() { return productName; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getSubtotal() { return subtotal; }
}
