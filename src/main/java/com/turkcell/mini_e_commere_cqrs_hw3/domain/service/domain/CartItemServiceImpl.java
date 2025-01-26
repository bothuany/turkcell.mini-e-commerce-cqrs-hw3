package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService{
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem getById(Integer id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
