package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart getById(Integer id);
    List<Cart> getAll();
    void resetCart(Integer cartId);
    int getCartIdByUserId(UUID userId);
    Object getMyCart(UUID activeUserId);
}
