package com.example.fintest.controller;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.fintest.consts.XSourceEnum;
import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.controller.dto.UserResponse;
import com.example.fintest.service.UserService;
import com.example.fintest.validation.marker.BankValidation;
import com.example.fintest.validation.marker.GosuslugiValidation;
import com.example.fintest.validation.marker.MailValidation;
import com.example.fintest.validation.marker.MobileValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@Tag(name = "Пользователи")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * Создание заявки на испытание.
     *
     * @param userRequest
     *         данные редактирования пользователя
     *
     * @return DTO сохраненного пользователя
     */
    @PostMapping(headers = {"x-source=" + XSourceEnum.MAIL})
    public ResponseEntity<UserResponse> createMail(
            @Validated({MailValidation.class}) @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping(headers = {"x-source=" + XSourceEnum.MOBILE})
    public ResponseEntity<UserResponse> createMobile(
            @Validated({MobileValidation.class}) @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping(headers = {"x-source=" + XSourceEnum.BANK})
    public ResponseEntity<UserResponse> createBank(
            @Validated({BankValidation.class}) @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping(headers = {"x-source=" + XSourceEnum.GOSUSLUGI})
    public ResponseEntity<UserResponse> createGosuslugi(
            @Validated({GosuslugiValidation.class}) @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param userId
     *         ID пользователя.
     *
     * @return DTO пользователя.
     */
    @Operation(
            summary = "Получение учетной записи пользователя по id",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserResponse.class))
                    }
            )
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> get(
            @PathVariable @Parameter(description = "ID пользователя") UUID userId
    ) {
        try {
            return ResponseEntity.ok(userService.get(userId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Получение пользователей по критерию.
     *
     * @param request
     *         Параметры фильтрации.
     * @param pageable
     *         Информация о пагинации.
     *
     * @return Список DTO сертификатов.
     */
    @Operation(summary = "Получение списка пользователей")
    @GetMapping
    public Page<UserResponse> get(
            @ParameterObject UserFilterRequest request,
            @PageableDefault(size = 25, sort = {"lastName", "firstName"}, direction = Sort.Direction.DESC)
            @ParameterObject Pageable pageable
    ) {
        return userService.get(request, pageable);
    }
}
