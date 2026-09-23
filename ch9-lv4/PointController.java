package com.example.point.controller;

import com.example.point.dto.request.PointAddRequest;
import com.example.point.dto.request.PointUseRequest;
import com.example.point.dto.response.MemberPointResponse;
import com.example.point.dto.response.PointHistoryResponse;
import com.example.point.service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointController {

    private static final Logger logger = LoggerFactory.getLogger(PointController.class);

    @Autowired
    private PointService pointService;

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberPointResponse> findMemberById(
            @PathVariable Integer memberId) {
        logger.info("GET /api/points/members/{}", memberId);
        return ResponseEntity.ok(pointService.findMemberById(memberId));
    }

    @PostMapping("/members/{memberId}/add")
    public ResponseEntity<MemberPointResponse> addPoints(
            @PathVariable Integer memberId,
            @RequestBody @Valid PointAddRequest request) {
        logger.info("POST /api/points/members/{}/add", memberId);
        return ResponseEntity.ok(pointService.addPoints(memberId, request));
    }

    @PostMapping("/members/{memberId}/use")
    public ResponseEntity<MemberPointResponse> usePoints(
            @PathVariable Integer memberId,
            @RequestBody @Valid PointUseRequest request) {
        logger.info("POST /api/points/members/{}/use", memberId);
        return ResponseEntity.ok(pointService.usePoints(memberId, request));
    }

    @GetMapping("/members/{memberId}/history")
    public ResponseEntity<List<PointHistoryResponse>> getHistory(
            @PathVariable Integer memberId) {
        logger.info("GET /api/points/members/{}/history", memberId);
        return ResponseEntity.ok(pointService.getHistory(memberId));
    }
}
