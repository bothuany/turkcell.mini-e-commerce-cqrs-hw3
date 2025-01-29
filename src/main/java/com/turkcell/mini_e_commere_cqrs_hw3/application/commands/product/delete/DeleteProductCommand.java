package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthorizedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductCommand implements Command<Void>, AuthorizedRequest {
    private int id;

    @Override
    public List<String> getRequiredRoles() {
        return List.of(OperationClaims.seller.name());
    }
}
