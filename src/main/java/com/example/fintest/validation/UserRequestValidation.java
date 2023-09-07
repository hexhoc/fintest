package com.example.fintest.validation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import com.example.fintest.consts.XSourceEnum;
import com.example.fintest.controller.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRequestValidation {

    public static String isValid(UserRequest userRequest, String xSource) {
        var message = "";
        switch (xSource) {
            case (XSourceEnum.MAIL) -> {
                if (isBlank(userRequest.getFirstName())
                    || isBlank(userRequest.getEmail())) {
                    message = "First name and email is required";
                }
            }
            case (XSourceEnum.MOBILE) -> {
                if (isBlank(userRequest.getPhone())) {
                    message = "Phone is required";
                }
            }
            case (XSourceEnum.BANK) -> {
                if (isBlank(userRequest.getBankId())
                        || isBlank(userRequest.getFirstName())
                        || isBlank(userRequest.getLastName())
                        || isBlank(userRequest.getMiddleName())
                        || isBlank(userRequest.getBirthdate())
                        || isBlank(userRequest.getPassportNumber())
                ) {
                    message = "bankId, firstName, lastName, middleName, birthdate, passportNumber is required.";
                }
            }
            case (XSourceEnum.GOSUSLUGI) -> {
                if (isBlank(userRequest.getBankId())
                        || isBlank(userRequest.getFirstName())
                        || isBlank(userRequest.getLastName())
                        || isBlank(userRequest.getMiddleName())
                        || isBlank(userRequest.getBirthdate())
                        || isBlank(userRequest.getPassportNumber())
                        || isBlank(userRequest.getBirthPlace())
                        || isBlank(userRequest.getPhone())
                        || isBlank(userRequest.getRegistrationAddress())
                ) {
                    message = "bankId, firstName, lastName, middleName, birthdate, passportNumber, birthPlace, phone, registrationAddress is required";
                }
            }
            default -> {
                message = "";
            }
        };

        return message;
    }

    private static boolean isBlank(String s) {
        return Objects.isNull(s) || s.isEmpty();
    }

    private static boolean isBlank(LocalDate s) {
        return Objects.isNull(s);
    }

}