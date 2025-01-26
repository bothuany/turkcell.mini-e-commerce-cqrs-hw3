package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OrderRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllUserOrders(UUID userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
    }

}
