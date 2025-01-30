package com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.validation;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(3)
public class ValidationMiddleware implements Command.Middleware {
    private final Validator validator;

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        Set<ConstraintViolation<C>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
        return next.invoke();
    }
}
