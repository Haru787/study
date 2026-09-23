package com.example.order.dto.response;

import com.example.order.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Integer id;
    private String orderNo;
    private Integer customerId;
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private String status;
    private String shippingAddress;
    private String paymentMethod;
    private LocalDateTime orderedAt;
    private List<OrderDetailResponse> details;

    public static OrderResponse from(OrderEntity entity, List<OrderDetailResponse> details) {
        OrderResponse response = new OrderResponse();
        response.id             = entity.getId();
        response.orderNo        = entity.getOrderNo();
        response.customerId     = entity.getCustomerId();
        response.totalAmount    = entity.getTotalAmount();
        response.taxAmount      = entity.getTaxAmount();
        response.status         = entity.getStatus();
        response.shippingAddress = entity.getShippingAddress();
        response.paymentMethod  = entity.getPaymentMethod();
        response.orderedAt      = entity.getOrderedAt();
        response.details        = details;
        return response;
    }

    public Integer getId() { return id; }
    public String getOrderNo() { return orderNo; }
    public Integer getCustomerId() { return customerId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getTaxAmount() { return taxAmount; }
    public String getStatus() { return status; }
    public String getShippingAddress() { return shippingAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getOrderedAt() { return orderedAt; }
    public List<OrderDetailResponse> getDetails() { return details; }
}
