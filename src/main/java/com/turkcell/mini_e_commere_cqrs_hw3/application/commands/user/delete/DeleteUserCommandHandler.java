package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserCommandHandler implements Command.Handler<DeleteUserCommand, Void>{
    private final UserRepository userRepository;
    @Override
    public Void handle(DeleteUserCommand deleteUserCommand) {
        userRepository.deleteById(deleteUserCommand.getId());
        return null;
    }
}
