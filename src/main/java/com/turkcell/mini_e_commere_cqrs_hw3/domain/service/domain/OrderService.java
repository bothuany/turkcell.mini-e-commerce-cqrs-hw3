package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllUserOrders(UUID id);

    Order getOrderById(Integer id);

}
