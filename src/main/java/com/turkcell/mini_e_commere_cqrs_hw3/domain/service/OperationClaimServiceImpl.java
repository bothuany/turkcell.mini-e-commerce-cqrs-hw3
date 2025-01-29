package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OperationClaimBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OperationClaimServiceImpl implements OperationClaimService {
    private final OperationClaimRepository operationClaimRepository;
    private final OperationClaimBusinessRules operationClaimBusinessRules;

    @Override
    public OperationClaim getOperationClaimByName(String name) {
        operationClaimBusinessRules.operationClaimMustExist(name);
        return operationClaimRepository.findByName(name).orElseThrow();
    }

    @Override
    public void addIfNotExistsOperationClaim(String name) {
        try {
            operationClaimBusinessRules.operationClaimMustExist(name);
        } catch (BusinessException e) {
            operationClaimRepository.save(new OperationClaim(name));
        }
    }
}
