package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;


import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
