package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthorizedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;

import java.util.List;

public record GetAllCartsQuery() implements Command<List<CartListingDto>>, AuthorizedRequest {
    @Override
    public List<String> getRequiredRoles() {
        return List.of(OperationClaims.admin.name());
    }
}
