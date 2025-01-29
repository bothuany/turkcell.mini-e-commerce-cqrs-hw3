package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;

import java.util.UUID;

public record GetUserByIdQuery(UUID userId) implements Command<UserListingDto> {
}
