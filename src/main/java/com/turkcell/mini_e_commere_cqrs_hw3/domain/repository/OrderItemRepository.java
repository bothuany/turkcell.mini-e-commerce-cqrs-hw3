package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;


import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    boolean existsByProductId(Integer productId);
}
