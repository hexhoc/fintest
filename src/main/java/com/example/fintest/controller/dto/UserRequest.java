package com.example.fintest.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO создания / редактирования пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

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
    @Pattern(regexp = "^\\d{4} \\d{6}$", message = "Номер и серия паспорта должны быть в формате XXXX XXXXXX")
    private String passportNumber;

    @Parameter(description = "Место рождения")
    private String birthPlace;

    @Parameter(description = "Телефон")
    @Pattern(regexp = "^7\\d{10}$", message = "Номер телефона должен быть в формате 7XXXXXXXXX")
    private String phone;

    @Parameter(description = "Почта")
    private String email;

    @Parameter(description = "Адрес регистрации")
    private String registrationAddress;

    @Parameter(description = "Адрес проживания")
    private String residentialAddress;

}
