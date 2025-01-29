package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.AuthUserDto;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.core.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService.getRoles;

@Component
@RequiredArgsConstructor
public class LoginCommandHandler implements Command.Handler<LoginCommand, AuthUserDto>{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUserDto handle(LoginCommand loginCommand) {
        User dbUser = userRepository.findByUsername(loginCommand.getUsername())
                .orElseThrow(() -> new BusinessException("User not found."));

        boolean isPasswordCorrect = passwordEncoder
                .matches(loginCommand.getPassword(), dbUser.getPassword());

        if(!isPasswordCorrect)
            throw new BusinessException("Invalid or wrong credentials.");

        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setToken(this.jwtService.generateToken(dbUser.getUsername(), getRoles(dbUser)));
        return authUserDto;
    }
}
