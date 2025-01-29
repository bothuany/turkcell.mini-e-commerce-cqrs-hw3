package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getbyid;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;

import an.awesome.pipelinr.Command;

@Component
@RequiredArgsConstructor
public class GetUserByIdQueryHandler implements Command.Handler<GetUserByIdQuery, UserListingDto> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserListingDto handle(GetUserByIdQuery getUserByIdQuery) {
        User user = userRepository.findById(getUserByIdQuery.userId())
                .orElseThrow(() -> new BusinessException("User not found"));
        return modelMapper.map(user, UserListingDto.class);
    }
}
