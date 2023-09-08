package com.example.fintest.controller;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.controller.dto.UserResponse;
import com.example.fintest.service.UserService;
import com.example.fintest.validation.UserRequestValidation;
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
    //TODO: Сделать перегрузку метода в зависимости от заголовка. В каждом перегруженном методе нужно сделать проверку в зависимости от маркерного интерфейса которым помечены проверяемые поля.@Validated({MailValidation.class}
    public ResponseEntity<Object> create(
            @RequestHeader("x-source") String xSource,
            @Valid @RequestBody UserRequest userRequest) {
        var message = UserRequestValidation.isValid(userRequest, xSource);
        if (!message.isEmpty()) {
            return ResponseEntity.badRequest().body(message);
        }

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
