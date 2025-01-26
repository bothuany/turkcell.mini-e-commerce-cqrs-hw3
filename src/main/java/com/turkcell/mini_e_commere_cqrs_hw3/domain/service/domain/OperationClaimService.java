package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;

import java.util.List;
import java.util.UUID;

public interface OperationClaimService {
    void addOperationClaim(String name);
    void deleteOperationClaim(String name);
    void updateOperationClaim(UUID id, String name);
    List<OperationClaim> getAllOperationClaims();
    OperationClaim getOperationClaimByName(String name);
    void addIfNotExistsOperationClaim(String name);

}
