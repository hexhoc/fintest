package com.example.fintest.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.controller.dto.UserRequest;
import com.example.fintest.entity.User;
import com.example.fintest.mapper.UserMapper;
import com.example.fintest.repository.UserRepository;
import com.example.fintest.repository.predicate.UserSpecification;
import com.example.fintest.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class );;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test find all users by criteria")
    void findAllByCriteria() {
        when(userRepository.findAll(Mockito.any(UserSpecification.class), Mockito.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(user())));

        var result = userService.get(userFilterRequest(),PageRequest.of(0, 25));

        assertThat(result).hasSize(1);

        var userResponse = result.get().findFirst().orElseThrow();

        assertEquals("Petr", userResponse.getFirstName());
        assertEquals("Petrov", userResponse.getLastName());
        assertEquals("Petrovich", userResponse.getMiddleName());
        assertEquals("79123456789", userResponse.getPhone());
        assertEquals("test@test.com", userResponse.getEmail());

    }

    @Test
    @DisplayName("Test find all users by criteria")
    void findById() {
        when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user()));

        var userResponse = userService.get(UUID.randomUUID());

        assertEquals("Petr", userResponse.getFirstName());
        assertEquals("Petrov", userResponse.getLastName());
        assertEquals("Petrovich", userResponse.getMiddleName());
        assertEquals("79123456789", userResponse.getPhone());
        assertEquals("test@test.com", userResponse.getEmail());

    }

    @Test
    @DisplayName("Test create user")
    void create() {
        when(userRepository.save(Mockito.any())).thenReturn(user());

        var userResponse = userService.create(userRequest());
        assertEquals(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"), userResponse.getId());
    }

    User user() {
        var u = new User();
        u.setId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"));
        u.setBankId("c589fd51-98d1-4b39-b5a0-fac8d309000d");
        u.setFirstName("Petr");
        u.setLastName("Petrov");
        u.setMiddleName("Petrovich");
        u.setBirthdate(LocalDate.of(1980, 1, 1));
        u.setPassportNumber("1234 123456");
        u.setBirthPlace("Russia, Moscow");
        u.setPhone("79123456789");
        u.setEmail("test@test.com");
        u.setRegistrationAddress("г. Москва, Козицкий переулок, 1А, Москва, 125009");
        u.setResidentialAddress("г. Москва, Козицкий переулок, 1А, Москва, 125009");

        return u;
    }


    UserFilterRequest userFilterRequest() {
        var u = new UserFilterRequest();
        u.setFirstName("Petr");
        u.setLastName("Petrov");
        u.setMiddleName("Petrovich");
        u.setPhone("+79123456789");
        u.setEmail("test@test.com");

        return u;
    }

    UserRequest userRequest() {
        var u = new UserRequest();
        u.setBankId("c589fd51-98d1-4b39-b5a0-fac8d309000d");
        u.setFirstName("Petr");
        u.setLastName("Petrov");
        u.setMiddleName("Petrovich");
        u.setBirthdate(LocalDate.of(1980, 1, 1));
        u.setPassportNumber("1234 123456");
        u.setBirthPlace("Russia, Moscow");
        u.setPhone("79123456789");
        u.setEmail("test@test.com");
        u.setRegistrationAddress("г. Москва, Козицкий переулок, 1А, Москва, 125009");
        u.setResidentialAddress("г. Москва, Козицкий переулок, 1А, Москва, 125009");

        return u;
    }
}