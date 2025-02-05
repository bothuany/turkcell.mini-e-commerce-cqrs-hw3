package com.turkcell.mini_e_commere_cqrs_hw3.core.web;


import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
public abstract class BaseController {
    protected final Pipeline pipeline;

    protected final boolean hasOperationClaims(UserDetails userDetails, OperationClaims operationClaim) {
        return userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(operationClaim.name()));
    }
}
