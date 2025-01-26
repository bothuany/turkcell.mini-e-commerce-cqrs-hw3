package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand implements Command<UserListingDto> {
    private UUID id;
    private String name;
    private String surname;
    private String password;
} 