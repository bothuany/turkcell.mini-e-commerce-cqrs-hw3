package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;

import java.util.List;

public record GetAllUsersQuery() implements Command<List<UserListingDto>> {
}
