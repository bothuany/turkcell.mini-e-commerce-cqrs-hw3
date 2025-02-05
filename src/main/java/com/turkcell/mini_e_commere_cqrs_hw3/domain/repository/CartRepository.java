package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByCustomerId(UUID customerId);
}
