package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.operationClaim.create;

import an.awesome.pipelinr.Command;

public record CreateOperationClaimIfNotExistsCommand(String name) implements Command<Void> {
}
