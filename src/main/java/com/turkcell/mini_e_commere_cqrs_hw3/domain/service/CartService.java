package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart getById(Integer id);
    Cart update(Cart cart);
    List<Cart> getAll();
    void resetCart(Integer cartId);
    boolean existsById(Integer id);
    Cart getCartByCustomerId(UUID customerId);
}
