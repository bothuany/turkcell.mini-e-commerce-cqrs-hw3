package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetByIdCartQueryHandler implements Command.Handler<GetByIdCartQuery, CartListingDto> {
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public CartListingDto handle(GetByIdCartQuery getByIdCartQuery) {
        return modelMapper.map(cartService.getById(getByIdCartQuery.id()), CartListingDto.class);
    }
}