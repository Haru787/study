package com.example.order.controller;

import com.example.order.dto.request.OrderCreateRequest;
import com.example.order.dto.response.OrderResponse;
import com.example.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer id) {
        logger.info("GET /api/orders/{}", id);
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> findByCustomer(
            @PathVariable Integer customerId) {
        logger.info("GET /api/orders/customer/{}", customerId);
        return ResponseEntity.ok(orderService.findByCustomer(customerId));
    }

    @PostMapping("/{id}/ship")
    public ResponseEntity<Void> ship(@PathVariable Integer id) {
        logger.info("POST /api/orders/{}/ship", id);
        orderService.ship(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Integer id) {
        logger.info("POST /api/orders/{}/cancel", id);
        orderService.cancel(id);
        return ResponseEntity.ok().build();
    }
}
