package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllCartsQueryHandler implements Command.Handler<GetAllCartsQuery, List<CartListingDto>> {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CartListingDto> handle(GetAllCartsQuery getAllCartsQuery) {
        return cartRepository.findAll().stream()
                .map(cart -> modelMapper.map(cart, CartListingDto.class))
                .collect(Collectors.toList());
    }
}