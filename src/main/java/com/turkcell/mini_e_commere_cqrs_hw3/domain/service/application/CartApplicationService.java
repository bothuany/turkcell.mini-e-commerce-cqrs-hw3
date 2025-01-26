package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;

import java.util.List;

public interface CartApplicationService {
    CartListingDto getById(Integer id);
    List<CartListingDto> getAll();
    CartListingDto getMyCart();
}
