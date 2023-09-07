package com.example.fintest.repository.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.fintest.controller.dto.UserFilterRequest;
import com.example.fintest.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * Строитель предикатов для фильтрации пользователей.
 */
@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {
    private final UserFilterRequest userFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (Objects.nonNull(userFilter.getFirstName())) {
            predicates.add(builder.equal(root.get("firstName"), String.valueOf(userFilter.getFirstName())));
        }
        if (Objects.nonNull(userFilter.getLastName())) {
            predicates.add(builder.equal(root.get("lastName"), String.valueOf(userFilter.getLastName())));
        }
        if (Objects.nonNull(userFilter.getMiddleName())) {
            predicates.add(builder.equal(root.get("middleName"), String.valueOf(userFilter.getMiddleName())));
        }
        if (Objects.nonNull(userFilter.getPhone())) {
            predicates.add(builder.equal(root.get("phone"), String.valueOf(userFilter.getPhone())));
        }
        if (Objects.nonNull(userFilter.getEmail())) {
            predicates.add(builder.equal(root.get("email"), String.valueOf(userFilter.getEmail())));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}