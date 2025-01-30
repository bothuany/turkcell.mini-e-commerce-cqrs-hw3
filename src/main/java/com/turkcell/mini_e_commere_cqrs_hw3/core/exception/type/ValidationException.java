package com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {
    private final Set<? extends ConstraintViolation<?>> violations;
}