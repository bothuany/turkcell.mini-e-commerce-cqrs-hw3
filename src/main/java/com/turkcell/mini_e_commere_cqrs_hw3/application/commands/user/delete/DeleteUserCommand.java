package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.delete;

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
public class DeleteUserCommand implements Command<Void> {
    private UUID id;
}
