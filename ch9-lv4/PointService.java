package com.example.point.service;

import com.example.point.dto.request.PointAddRequest;
import com.example.point.dto.request.PointUseRequest;
import com.example.point.dto.response.MemberPointResponse;
import com.example.point.dto.response.PointHistoryResponse;
import com.example.point.entity.MemberEntity;
import com.example.point.entity.PointHistoryEntity;
import com.example.point.exception.InsufficientPointsException;
import com.example.point.exception.MemberNotFoundException;
import com.example.point.repository.MemberMapper;
import com.example.point.repository.PointHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointService {

    private static final Logger logger = LoggerFactory.getLogger(PointService.class);

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PointHistoryMapper pointHistoryMapper;

    public MemberPointResponse findMemberById(Integer memberId) {
        logger.info("会員検索: memberId={}", memberId);
        return memberMapper.findById(memberId)
                .map(MemberPointResponse::from)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public MemberPointResponse addPoints(Integer memberId, PointAddRequest request) {
        logger.info("ポイント付与: memberId={}, points={}", memberId, request.getPoints());

        MemberEntity member = memberMapper.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        int newBalance = member.getPointBalance() + request.getPoints();
        memberMapper.updatePointBalance(memberId, newBalance);

        PointHistoryEntity history = new PointHistoryEntity();
        history.setMemberId(memberId);
        history.setPointChange(request.getPoints());
        history.setReason(request.getReason());
        history.setBalanceAfter(newBalance);
        history.setCreatedAt(LocalDateTime.now());
        pointHistoryMapper.insert(history);

        logger.info("ポイント付与完了: memberId={}, balanceAfter={}", memberId, newBalance);
        member.setPointBalance(newBalance);
        return MemberPointResponse.from(member);
    }

    @Transactional
    public MemberPointResponse usePoints(Integer memberId, PointUseRequest request) {
        logger.info("ポイント利用: memberId={}, usePoints={}", memberId, request.getPoints());

        MemberEntity member = memberMapper.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        int currentBalance = member.getPointBalance();

        if (currentBalance < request.getPoints()) {
            logger.warn("ポイント不足: memberId={}, usePoints={}, currentBalance={}",
                    memberId, request.getPoints(), currentBalance);
            throw new InsufficientPointsException(currentBalance, request.getPoints());
        }

        int newBalance = currentBalance - request.getPoints();
        memberMapper.updatePointBalance(memberId, newBalance);

        PointHistoryEntity history = new PointHistoryEntity();
        history.setMemberId(memberId);
        history.setPointChange(-request.getPoints());
        history.setReason(request.getReason());
        history.setBalanceAfter(newBalance);
        history.setCreatedAt(LocalDateTime.now());
        pointHistoryMapper.insert(history);

        logger.info("ポイント利用完了: memberId={}, balanceAfter={}", memberId, newBalance);
        member.setPointBalance(newBalance);
        return MemberPointResponse.from(member);
    }

    public List<PointHistoryResponse> getHistory(Integer memberId) {
        logger.info("ポイント履歴取得: memberId={}", memberId);
        memberMapper.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return pointHistoryMapper.findByMemberId(memberId)
                .stream().map(PointHistoryResponse::from).collect(Collectors.toList());
    }
}
