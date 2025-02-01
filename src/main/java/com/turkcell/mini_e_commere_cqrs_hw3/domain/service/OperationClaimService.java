package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;

import java.util.List;

public interface OperationClaimService {
    OperationClaim getByName(String name);

    List<OperationClaim> getAll();

    void update(OperationClaim operationClaim);

    void deleteByName(String name);

    void add(OperationClaim operationClaim);
}
