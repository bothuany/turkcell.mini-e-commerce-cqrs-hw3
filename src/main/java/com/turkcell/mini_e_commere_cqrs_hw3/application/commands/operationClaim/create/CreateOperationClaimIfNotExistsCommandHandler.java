package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.operationClaim.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OperationClaimService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOperationClaimIfNotExistsCommandHandler implements Command.Handler<CreateOperationClaimIfNotExistsCommand, Void>{
    private final OperationClaimService operationClaimService;
    private final ModelMapper modelMapper;

    @Override
    public Void handle(CreateOperationClaimIfNotExistsCommand createOperationClaimIfNotExistsCommand) {
        OperationClaim operationClaim = modelMapper.map(createOperationClaimIfNotExistsCommand, OperationClaim.class);
        operationClaimService.add(operationClaim);
        return null;
    }
}
