package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OrderItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order create(Order order);

    Order update(Order order);

    Order updateOrderState(Integer id);

    List<Order> getAllUserOrders(UUID id);

    Order getById(Integer id);

    void delete(Integer orderId);

    void handleOrderStatusTransition(OrderStatus currentStatus, Order order);

    List<OrderItem> createOrderItems(Cart cart, Order order);

    Order createOrderForUser(User user);
}
