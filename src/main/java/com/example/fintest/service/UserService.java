package com.example.fintest.service;

import java.util.UUID;

import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.controller.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Интерфейс сервиса для работы с пользователями.
 */
public interface UserService {

    /**
     * Создать пользователя.
     *
     * @param request
     *         Данные пользователя.
     *
     * @return Созданный пользователь.
     */
    UserResponse create(UserRequest request);

    /**
     * Получает пользователя по идентификатору.
     *
     * @param userId
     *         Идентификатор.
     *
     * @return Пользователь.
     */
    UserResponse get(UUID userId);

    /**
     * Получает список пользователей по фильтру.
     *
     * @param request
     *         Параметры фильтрации.
     * @param pageable
     *         Информация о пагинации.
     *
     * @return Список пользователей + количество записей, количество страниц, количество записей на странице.
     */
    Page<UserResponse> get(UserFilterRequest request, Pageable pageable);

}
