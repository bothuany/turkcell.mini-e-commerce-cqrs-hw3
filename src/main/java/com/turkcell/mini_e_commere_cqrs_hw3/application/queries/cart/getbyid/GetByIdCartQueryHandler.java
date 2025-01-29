package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetByIdCartQueryHandler implements Command.Handler<GetByIdCartQuery, CartListingDto> {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartListingDto handle(GetByIdCartQuery getByIdCartQuery) {
        return modelMapper.map(cartRepository.findById(getByIdCartQuery.id()).orElse(null), CartListingDto.class);
    }
}