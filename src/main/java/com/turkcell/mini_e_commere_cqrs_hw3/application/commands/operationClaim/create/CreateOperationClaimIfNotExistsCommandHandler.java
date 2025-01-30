package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.operationClaim.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OperationClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOperationClaimIfNotExistsCommandHandler implements Command.Handler<CreateOperationClaimIfNotExistsCommand, Void>{
    private final OperationClaimRepository operationClaimRepository;

    @Override
    public Void handle(CreateOperationClaimIfNotExistsCommand createOperationClaimIfNotExistsCommand) {
        if (operationClaimRepository.findByName(createOperationClaimIfNotExistsCommand.name()).isPresent()) {
            return null;
        }
        operationClaimRepository.save(new OperationClaim(createOperationClaimIfNotExistsCommand.name()));
        return null;
    }
}
