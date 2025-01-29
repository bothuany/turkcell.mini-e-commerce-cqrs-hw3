package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthorizedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;

import java.util.List;
import java.util.UUID;

public record GetMyCartQuery(UUID userId) implements Command<CartListingDto>, AuthorizedRequest {
    @Override
    public List<String> getRequiredRoles() {
        return List.of(OperationClaims.customer.name());
    }
}
