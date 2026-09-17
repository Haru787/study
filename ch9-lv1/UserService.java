package com.example.user.service;

import com.example.user.dto.request.UserCreateRequest;
import com.example.user.dto.response.UserResponse;
import com.example.user.entity.UserEntity;
import com.example.user.exception.UserNotFoundException;
import com.example.user.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public UserResponse findById(Integer id) {
        logger.info("ユーザー検索: id={}", id);
        return userMapper.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserResponse> findAll() {
        logger.info("全ユーザー取得");
        return userMapper.findAll().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public List<UserResponse> findByDepartment(String department) {
        logger.info("部署別ユーザー取得: department={}", department);
        return userMapper.findByDepartment(department).stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        logger.info("ユーザー作成: username={}", request.getUsername());
        UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setFullName(request.getFullName());
        entity.setDepartment(request.getDepartment());
        entity.setRole(request.getRole());
        entity.setIsActive(true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        // ※実際はMapperのinsertを呼び出す
        return UserResponse.from(entity);
    }
}
