package com.example.fintest.controller.dto;

import java.time.LocalDate;

import com.example.fintest.validation.marker.BankValidation;
import com.example.fintest.validation.marker.GosuslugiValidation;
import com.example.fintest.validation.marker.MailValidation;
import com.example.fintest.validation.marker.MobileValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    /*
                if (isBlank(userRequest.getBankId())
                        || isBlank(userRequest.getFirstName())
                        || isBlank(userRequest.getLastName())
                        || isBlank(userRequest.getMiddleName())
                        || isBlank(userRequest.getBirthdate())
                        || isBlank(userRequest.getPassportNumber())
                        || isBlank(userRequest.getBirthPlace())
                        || isBlank(userRequest.getPhone())
                        || isBlank(userRequest.getRegistrationAddress())

     */

    @NotBlank(groups = {BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Банковский идентификатор>")
    private String bankId;

    @NotBlank(groups = {MailValidation.class, BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Имя")
    private String firstName;

    @NotBlank(groups = {BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Фамилия")
    private String lastName;

    @NotBlank(groups = {BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Отчество")
    private String middleName;

    @NotNull(groups = {BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Дата рождения")
    private LocalDate birthdate;

    @NotBlank(groups = {BankValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Паспорт (Серия и номер")
    @Pattern(regexp = "^\\d{4} \\d{6}$", message = "Номер и серия паспорта должны быть в формате XXXX XXXXXX")
    private String passportNumber;

    @NotBlank(groups = {GosuslugiValidation.class})
    @Parameter(description = "Место рождения")
    private String birthPlace;

    @NotBlank(groups = {MobileValidation.class, GosuslugiValidation.class})
    @Parameter(description = "Телефон")
    @Pattern(regexp = "^7\\d{10}$", message = "Номер телефона должен быть в формате 7XXXXXXXXX")
    private String phone;

    @NotBlank(groups = {MailValidation.class})
    @Parameter(description = "Почта")
    private String email;

    @NotBlank(groups = {GosuslugiValidation.class})
    @Parameter(description = "Адрес регистрации")
    private String registrationAddress;

    @Parameter(description = "Адрес проживания")
    private String residentialAddress;

}
