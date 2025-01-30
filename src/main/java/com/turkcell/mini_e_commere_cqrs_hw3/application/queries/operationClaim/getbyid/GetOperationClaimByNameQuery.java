package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.operationClaim.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;

public record GetOperationClaimByNameQuery(String name) implements Command<OperationClaim> {
}
