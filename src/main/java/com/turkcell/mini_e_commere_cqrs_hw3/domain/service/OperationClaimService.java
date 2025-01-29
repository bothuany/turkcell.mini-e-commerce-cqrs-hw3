package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;

public interface OperationClaimService {
    OperationClaim getOperationClaimByName(String name);
    void addIfNotExistsOperationClaim(String name);
}
