package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void resetCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartRepository.save(cart);
    }
}
