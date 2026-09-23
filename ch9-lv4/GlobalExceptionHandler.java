package com.example.point.exception.handler;

import com.example.point.exception.InsufficientPointsException;
import com.example.point.exception.MemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMemberNotFoundException(
            MemberNotFoundException ex) {
        logger.warn("会員が見つかりません: memberId={}", ex.getMemberId());
        Map<String, Object> body = new HashMap<>();
        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(InsufficientPointsException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientPointsException(
            InsufficientPointsException ex) {
        logger.warn("ポイント不足: currentBalance={}, requiredPoints={}",
                ex.getCurrentBalance(), ex.getRequiredPoints());
        Map<String, Object> body = new HashMap<>();
        body.put("status", 422);
        body.put("error", "Unprocessable Entity");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message", "入力値が不正です");
        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        logger.error("予期しないエラーが発生しました", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("status", 500);
        body.put("error", "Internal Server Error");
        body.put("message", "サーバーエラーが発生しました");
        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.internalServerError().body(body);
    }
}
