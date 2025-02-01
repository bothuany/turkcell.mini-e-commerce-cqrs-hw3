package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OperationClaimBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OperationClaimServiceImpl implements OperationClaimService {
    private final OperationClaimRepository operationClaimRepository;
    private final OperationClaimBusinessRules operationClaimBusinessRules;

    @Override
    public void add(OperationClaim operationClaim) {
        operationClaimBusinessRules.operationClaimNameMustBeUnique(operationClaim.getName());
        operationClaimRepository.save(operationClaim);
    }

    @Override
    public void deleteByName(String name) {
        operationClaimBusinessRules.operationClaimMustExist(name);
        operationClaimRepository.delete(operationClaimRepository.findByName(name).orElseThrow());
    }

    @Override
    public void update(OperationClaim operationClaim) {
        operationClaimBusinessRules.operationClaimMustExist(operationClaim.getName());
        operationClaimRepository.save(operationClaim);
    }

    @Override
    public List<OperationClaim> getAll() {
        return operationClaimRepository.findAll();
    }

    @Override
    public OperationClaim getByName(String name) {
        operationClaimBusinessRules.operationClaimMustExist(name);
        return operationClaimRepository.findByName(name).orElseThrow();
    }
}
