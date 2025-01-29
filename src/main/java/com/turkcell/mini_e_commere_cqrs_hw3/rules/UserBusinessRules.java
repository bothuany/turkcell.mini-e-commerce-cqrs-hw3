package com.turkcell.mini_e_commere_cqrs_hw3.rules;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void usernameMustNotExist(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new BusinessException("Username already exist");
        });
    }

    public void idMustExist(UUID id) {
        userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    }
}
