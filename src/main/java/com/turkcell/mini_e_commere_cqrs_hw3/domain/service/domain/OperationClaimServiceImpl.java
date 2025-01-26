package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OperationClaimBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OperationClaimServiceImpl implements OperationClaimService {
    private final OperationClaimRepository operationClaimRepository;
    private final OperationClaimBusinessRules operationClaimBusinessRules;

    @Override
    public void addOperationClaim(String name) {
        operationClaimRepository.save(new OperationClaim(name));
    }

    @Override
    public void deleteOperationClaim(String name) {
        operationClaimBusinessRules.operationClaimMustExist(name);
        operationClaimRepository.delete(operationClaimRepository.findByName(name).orElseThrow());
    }

    @Override
    public void updateOperationClaim(UUID id, String name) {
        operationClaimBusinessRules.operationClaimMustExist(name);
        OperationClaim operationClaim = operationClaimRepository.findById(id).orElseThrow();
        operationClaim.setName(name);
        operationClaimRepository.save(operationClaim);
    }

    @Override
    public List<OperationClaim> getAllOperationClaims() {
        return operationClaimRepository.findAll();
    }

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
            addOperationClaim(name);
        }
    }
}
