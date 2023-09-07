package com.example.fintest.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

/**
 * Сущность "Пользователи".
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "users")
public class User {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private UUID id;

    /**
     * идентификатор клиента в банке
     */
    @Column(name = "bank_id", nullable = false)
    private String bankId;

    /**
     * имя
     */
    @ToString.Include
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * фамилия
     */
    @ToString.Include
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * отчетство
     */
    @ToString.Include
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    /**
     * дата рождения
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * паспорт (серия и номер)
     */
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    /**
     * место рождения
     */
    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    /**
     * номер телефона
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * email
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * адрес регистрации
     */
    @Column(name = "registration_address", nullable = false)
    private String registrationAddress;

    /**
     * адрес проживания
     */
    @Column(name = "residential_address", nullable = false)
    private String residentialAddress;

}
