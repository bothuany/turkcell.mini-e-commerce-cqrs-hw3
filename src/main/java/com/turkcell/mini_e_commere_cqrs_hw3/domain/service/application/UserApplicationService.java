package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.update.UpdateUserCommand;

import java.util.List;
import java.util.UUID;

public interface UserApplicationService {

    List<UserListingDto> getAll();

    UserListingDto getById(UUID id);
}
