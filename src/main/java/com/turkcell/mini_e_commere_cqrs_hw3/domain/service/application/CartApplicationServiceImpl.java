package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain.CartService;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartApplicationServiceImpl implements CartApplicationService {
    private final CartService cartService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public CartListingDto getById(Integer id) {
        return modelMapper.map(cartService.getById(id), CartListingDto.class);
    }

    @Override
    public List<CartListingDto> getAll() {
        return cartService.getAll().stream()
                .map(cart -> modelMapper.map(cart, CartListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartListingDto getMyCart() {
        return modelMapper.map(cartService.getMyCart(userService.getActiveUserId()), CartListingDto.class);
    }
}
