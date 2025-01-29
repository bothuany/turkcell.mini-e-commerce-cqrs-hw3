package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.UserBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMyCartQueryHandler implements Command.Handler<GetMyCartQuery, CartListingDto> {
    private final CartRepository cartRepository;
    private final UserBusinessRules userBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public CartListingDto handle(GetMyCartQuery getMyCartQuery) {
        userBusinessRules.idMustExist(getMyCartQuery.userId());
        return modelMapper.map(cartRepository.findByUserId(getMyCartQuery.userId()), CartListingDto.class);
    }
}