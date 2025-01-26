package com.turkcell.mini_e_commere_cqrs_hw3.rules;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartItemRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void cartIdMustExist(Integer id)
    {
        cartRepository.findById(id).orElseThrow(() -> new BusinessException("Cart not found"));
    }

    public void cartItemMustExist(Integer id)
    {
        cartItemRepository.findById(id).orElseThrow(() -> new BusinessException("Cart item not found"));
    }
}