package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.operationClaim.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OperationClaimBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOperationClaimByNameQueryHandler implements Command.Handler<GetOperationClaimByNameQuery, OperationClaim>{
    private final OperationClaimRepository operationClaimRepository;
    private final OperationClaimBusinessRules operationClaimBusinessRules;

    @Override
    public OperationClaim handle(GetOperationClaimByNameQuery query) {
        operationClaimBusinessRules.operationClaimMustExist(query.name());
        return operationClaimRepository.findByName(query.name()).orElseThrow();
    }
}
