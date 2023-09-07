package com.example.fintest.service.impl;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.controller.dto.UserResponse;
import com.example.fintest.mapper.UserMapper;
import com.example.fintest.repository.UserRepository;
import com.example.fintest.repository.predicate.UserSpecification;
import com.example.fintest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с пользователями.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        var userEntity = userMapper.toEntity(request);
        var saved = userRepository.save(userEntity);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse get(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + userId));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> get(UserFilterRequest request, Pageable pageable) {
        return userRepository.findAll(new UserSpecification(request), pageable)
                .map(userMapper::toDto);
    }

}
