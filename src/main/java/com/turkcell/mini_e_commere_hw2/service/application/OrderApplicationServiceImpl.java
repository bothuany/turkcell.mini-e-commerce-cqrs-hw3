package com.turkcell.mini_e_commere_hw2.service.application;

import com.turkcell.mini_e_commere_hw2.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_hw2.entity.Order;
import com.turkcell.mini_e_commere_hw2.service.domain.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService{
   private final OrderService orderService;
   private final ModelMapper modelMapper;

    @Override
    public OrderListingDto createOrder(UUID userId) {
        Order order = orderService.createOrder(userId);
        return modelMapper.map(order, OrderListingDto.class);
    }

    @Override
    public OrderListingDto updateOrderState(Integer id) {
        Order order = orderService.updateOrderState(id);
        return modelMapper.map(order, OrderListingDto.class);
    }

    @Override
    public List<OrderListingDto> getAllUserOrders(UUID userId) {
        List<Order> orders = orderService.getAllUserOrders(userId);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderListingDto.class))
                .collect(Collectors.toList());
    }
}
