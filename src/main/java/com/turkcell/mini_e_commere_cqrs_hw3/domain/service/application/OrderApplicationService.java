package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;

import java.util.List;
import java.util.UUID;

public interface OrderApplicationService {
    List<OrderListingDto> getAllUserOrders();
    List<OrderListingDto> getAllUserOrders(UUID userId);
    OrderListingDto getOrderById(Integer id);
}
