package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OrderItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OrderRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OrderStatus;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private static final LinkedList<OrderStatus> ORDER_STATUS_PROGRESSION = new LinkedList<>();

    static {
        ORDER_STATUS_PROGRESSION.add(OrderStatus.PREPARING);
        ORDER_STATUS_PROGRESSION.add(OrderStatus.SHIPPED);
        ORDER_STATUS_PROGRESSION.add(OrderStatus.DELIVERED);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderState(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
        OrderStatus currentStatus = order.getStatus();
        handleOrderStatusTransition(currentStatus, order);
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<Order> getAllUserOrders(UUID userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
    }

    @Override
    public void delete(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public void handleOrderStatusTransition(OrderStatus currentStatus, Order order) {
        int currentIndex = ORDER_STATUS_PROGRESSION.indexOf(currentStatus);

        if (currentIndex == -1 || currentIndex == ORDER_STATUS_PROGRESSION.size() - 1) {
            if (currentStatus == OrderStatus.DELIVERED) {
                throw new BusinessException("Order already delivered. No further status updates allowed.");
            }
            throw new BusinessException("Invalid order status transition.");
        }

        OrderStatus nextStatus = ORDER_STATUS_PROGRESSION.get(currentIndex + 1);
        order.setStatus(nextStatus);
    }

    @Override
    public List<OrderItem> createOrderItems(Cart cart, Order order) {
        return cart.getCartItems().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());
    }

    @Override
    public Order createOrderForUser(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PREPARING);
        order.setTotalPrice(user.getCart().getTotalPrice());
        return order;
    }
}
