package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;

public interface CartItemService {
    CartItem getById(Integer id);
    void delete(Integer id);
}
