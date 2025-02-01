package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserCommandHandler implements Command.Handler<DeleteUserCommand, Void>{
    private final UserService userService;
    @Override
    public Void handle(DeleteUserCommand deleteUserCommand) {
        userService.delete(deleteUserCommand.getId());
        return null;
    }
}
