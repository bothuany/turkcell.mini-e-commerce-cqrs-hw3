package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Cart getById(Integer id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public void resetCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartRepository.save(cart);
    }

    @Override
    public int getCartIdByUserId(UUID userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"))
                .getId();
    }

    @Override
    public Object getMyCart(UUID activeUserId) {
        return cartRepository.findByUserId(activeUserId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
