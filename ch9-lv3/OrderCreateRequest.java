package com.example.order.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderCreateRequest {

    @NotNull(message = "顧客IDは必須です")
    private Integer customerId;

    @NotBlank(message = "配送先住所は必須です")
    private String shippingAddress;

    @NotBlank(message = "支払方法は必須です")
    private String paymentMethod;

    @NotNull(message = "注文明細は必須です")
    @Size(min = 1, message = "1件以上の注文明細が必要です")
    private List<OrderDetailCreateRequest> details;

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public List<OrderDetailCreateRequest> getDetails() { return details; }
    public void setDetails(List<OrderDetailCreateRequest> details) { this.details = details; }

    public static class OrderDetailCreateRequest {

        @NotNull(message = "商品IDは必須です")
        private Integer productId;

        @NotNull(message = "数量は必須です")
        private Integer quantity;

        public Integer getProductId() { return productId; }
        public void setProductId(Integer productId) { this.productId = productId; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}
