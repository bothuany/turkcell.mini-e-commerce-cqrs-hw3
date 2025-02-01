package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserCommandHandler implements Command.Handler<UpdateUserCommand, UserListingDto> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserListingDto handle(UpdateUserCommand updateUserCommand) {
        User user = modelMapper.map(updateUserCommand, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user);
        return modelMapper.map(user, UserListingDto.class);
    }
}
