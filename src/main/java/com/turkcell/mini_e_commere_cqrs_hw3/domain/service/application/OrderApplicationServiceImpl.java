package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain.UserService;
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
   private final UserService userService;
   private final ModelMapper modelMapper;

    @Override
    public List<OrderListingDto> getAllUserOrders() {
        UUID activeUserId = userService.getActiveUserId();
        return getAllUserOrders(activeUserId);
    }

    @Override
    public List<OrderListingDto> getAllUserOrders(UUID userId) {
        List<Order> orders = orderService.getAllUserOrders(userId);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderListingDto getOrderById(Integer id) {
        Order order = orderService.getOrderById(id);
        return modelMapper.map(order, OrderListingDto.class);
    }
}
