package com.example.product.dto.response;

import java.util.List;

public class ProductListResponse {

    private int totalCount;
    private List<ProductResponse> products;

    public ProductListResponse(List<ProductResponse> products) {
        this.products   = products;
        this.totalCount = products.size();
    }

    public int getTotalCount() { return totalCount; }
    public List<ProductResponse> getProducts() { return products; }
}
