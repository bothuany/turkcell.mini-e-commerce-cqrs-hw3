package com.turkcell.mini_e_commere_cqrs_hw3.rules;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OperationClaimBusinessRules {
    private final OperationClaimRepository operationClaimRepository;

    public void operationClaimMustExist(String name) {
        operationClaimRepository.findByName(name).orElseThrow(() -> new BusinessException("Operation claim not found"));
    }

    public void operationClaimNameMustBeUnique(String name) {
        if (operationClaimRepository.existsByName(name)) {
            throw new BusinessException("Operation claim name must be unique");
        }
    }
}