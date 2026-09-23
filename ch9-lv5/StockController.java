package com.example.stock.controller;

import com.example.stock.dto.request.StockTransferRequest;
import com.example.stock.dto.response.StockResponse;
import com.example.stock.dto.response.StockTransferResponse;
import com.example.stock.dto.response.TransferHistoryResponse;
import com.example.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @GetMapping("/warehouse/{warehouseCode}")
    public ResponseEntity<List<StockResponse>> findByWarehouse(
            @PathVariable String warehouseCode) {
        logger.info("GET /api/stocks/warehouse/{}", warehouseCode);
        return ResponseEntity.ok(stockService.findByWarehouse(warehouseCode));
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<List<StockResponse>> findByProduct(
            @PathVariable String productCode) {
        logger.info("GET /api/stocks/product/{}", productCode);
        return ResponseEntity.ok(stockService.findByProduct(productCode));
    }

    @PostMapping("/transfer")
    public ResponseEntity<StockTransferResponse> transfer(
            @RequestBody @Valid StockTransferRequest request) {
        logger.info("POST /api/stocks/transfer: from={}, to={}, product={}",
                request.getFromWarehouseCode(), request.getToWarehouseCode(), request.getProductCode());
        return ResponseEntity.ok(stockService.transfer(request));
    }

    @GetMapping("/history/{warehouseCode}")
    public ResponseEntity<List<TransferHistoryResponse>> getTransferHistory(
            @PathVariable String warehouseCode) {
        logger.info("GET /api/stocks/history/{}", warehouseCode);
        return ResponseEntity.ok(stockService.getTransferHistory(warehouseCode));
    }
}
