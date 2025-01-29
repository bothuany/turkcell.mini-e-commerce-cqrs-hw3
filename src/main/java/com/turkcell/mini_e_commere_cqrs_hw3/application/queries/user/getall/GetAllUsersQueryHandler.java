package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getall;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;

import an.awesome.pipelinr.Command;

@Component
@RequiredArgsConstructor
public class GetAllUsersQueryHandler implements Command.Handler<GetAllUsersQuery, List<UserListingDto>> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserListingDto> handle(GetAllUsersQuery getAllUsersQuery) {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserListingDto.class))
                .collect(Collectors.toList());
    }
}
