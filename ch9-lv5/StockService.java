package com.example.stock.service;

import com.example.stock.dto.request.StockTransferRequest;
import com.example.stock.dto.response.StockResponse;
import com.example.stock.dto.response.StockTransferResponse;
import com.example.stock.dto.response.TransferHistoryResponse;
import com.example.stock.entity.StockEntity;
import com.example.stock.entity.StockTransferHistoryEntity;
import com.example.stock.exception.InsufficientStockException;
import com.example.stock.exception.StockNotFoundException;
import com.example.stock.repository.StockMapper;
import com.example.stock.repository.StockTransferHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockTransferHistoryMapper historyMapper;

    public List<StockResponse> findByWarehouse(String warehouseCode) {
        logger.info("倉庫別在庫取得: warehouseCode={}", warehouseCode);
        return stockMapper.findByWarehouse(warehouseCode).stream()
                .map(StockResponse::from)
                .collect(Collectors.toList());
    }

    public List<StockResponse> findByProduct(String productCode) {
        logger.info("商品別在庫取得: productCode={}", productCode);
        return stockMapper.findByProduct(productCode).stream()
                .map(StockResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public StockTransferResponse transfer(StockTransferRequest request) {
        logger.info("在庫移動開始: from={}, to={}, product={}, qty={}",
                request.getFromWarehouseCode(), request.getToWarehouseCode(),
                request.getProductCode(), request.getQuantity());

        // 移動元在庫チェック
        StockEntity fromStock = stockMapper.findByWarehouseAndProduct(
                request.getFromWarehouseCode(), request.getProductCode())
                .orElseThrow(() -> new StockNotFoundException(
                        request.getFromWarehouseCode(), request.getProductCode()));

        if (fromStock.getQuantity() < request.getQuantity()) {
            throw new InsufficientStockException(fromStock.getQuantity(), request.getQuantity());
        }

        // 移動先在庫チェック
        StockEntity toStock = stockMapper.findByWarehouseAndProduct(
                request.getToWarehouseCode(), request.getProductCode())
                .orElseThrow(() -> new StockNotFoundException(
                        request.getToWarehouseCode(), request.getProductCode()));

        int newFromQty = fromStock.getQuantity() - request.getQuantity();
        int newToQty   = toStock.getQuantity()   + request.getQuantity();

        stockMapper.updateQuantity(request.getFromWarehouseCode(),
                request.getProductCode(), newFromQty, request.getExecutedBy());
        stockMapper.updateQuantity(request.getToWarehouseCode(),
                request.getProductCode(), newToQty, request.getExecutedBy());

        // 移動履歴を記録
        String transferNo = "TRN-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        StockTransferHistoryEntity history = new StockTransferHistoryEntity();
        history.setTransferNo(transferNo);
        history.setFromWarehouseCode(request.getFromWarehouseCode());
        history.setToWarehouseCode(request.getToWarehouseCode());
        history.setProductCode(request.getProductCode());
        history.setQuantity(request.getQuantity());
        history.setStatus("完了");
        history.setExecutedBy(request.getExecutedBy());
        history.setExecutedAt(LocalDateTime.now());
        historyMapper.insert(history);

        StockTransferResponse response = new StockTransferResponse();
        response.setTransferNo(transferNo);
        response.setFromWarehouseCode(request.getFromWarehouseCode());
        response.setToWarehouseCode(request.getToWarehouseCode());
        response.setProductCode(request.getProductCode());
        response.setQuantity(request.getQuantity());
        response.setStatus("完了");
        response.setFromBalanceAfter(newFromQty);
        response.setToBalanceAfter(newToQty);

        logger.info("在庫移動完了: transferNo={}", transferNo);
        return response;
    }

    public List<TransferHistoryResponse> getTransferHistory(String warehouseCode) {
        logger.info("移動履歴取得: warehouseCode={}", warehouseCode);
        return historyMapper.findByWarehouse(warehouseCode).stream()
                .map(TransferHistoryResponse::from)
                .collect(Collectors.toList());
    }
}
