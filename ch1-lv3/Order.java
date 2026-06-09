// package com.example.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 注文ヘッダーエンティティ。
 */
public class Order {

    private final String orderId;
    private final String customerId;
    private final String customerName;
    private final List<OrderItem> items;
    private String status;
    private String note;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(String orderId, String customerId, String customerName) {
        this.orderId      = orderId;
        this.customerId   = customerId;
        this.customerName = customerName;
        this.items        = new ArrayList<>();
        this.status       = "受付";
        this.note         = null;
        this.createdAt    = LocalDateTime.now();
        this.updatedAt    = LocalDateTime.now();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        this.updatedAt = LocalDateTime.now();
    }

    public int calcTotal() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.calcSubtotal();
        }
        return total;
    }

    public String getOrderId()       { return orderId; }
    public String getCustomerId()    { return customerId; }
    public String getCustomerName()  { return customerName; }
    public List<OrderItem> getItems(){ return items; }
    public String getStatus()        { return status; }
    public String getNote()          { return note; }
    public void   setNote(String note)   { this.note = note; }
    public void   setStatus(String status){ this.status = status; }
    public LocalDateTime getCreatedAt()  { return createdAt; }
}
