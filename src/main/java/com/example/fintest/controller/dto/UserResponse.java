package com.example.fintest.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * DTO пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    @Parameter(description = "id пользователя")
    private UUID id;

    @Parameter(description = "Банковский идентификатор>")
    private String bankId;

    @Parameter(description = "Имя")
    private String firstName;

    @Parameter(description = "Фамилия")
    private String lastName;

    @Parameter(description = "Отчество")
    private String middleName;

    @Parameter(description = "Дата рождения")
    private LocalDate birthdate;

    @Parameter(description = "Паспорт (Серия и номер")
    private String passportNumber;

    @Parameter(description = "Место рождения")
    private String birthPlace;

    @Parameter(description = "Телефон")
    private String phone;

    @Parameter(description = "Почта")
    private String email;

    @Parameter(description = "Адрес регистрации")
    private String registrationAddress;

    @Parameter(description = "Адрес проживания")
    private String residentialAddress;

}
