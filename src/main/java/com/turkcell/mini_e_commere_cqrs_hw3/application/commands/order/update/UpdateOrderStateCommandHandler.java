package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OrderStatus;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrderStateCommandHandler implements Command.Handler<UpdateOrderStateCommand, OrderListingDto> {
    private final OrderService orderService;
    private final OrderBusinessRules orderBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public OrderListingDto handle(UpdateOrderStateCommand updateOrderStateCommand) {
        orderBusinessRules.checkOrderExist(updateOrderStateCommand.getOrderId());
        Order order = orderService.getById(updateOrderStateCommand.getOrderId());
        OrderStatus currentStatus = order.getStatus();
        orderService.handleOrderStatusTransition(currentStatus, order);
        orderService.update(order);

        return modelMapper.map(order, OrderListingDto.class);
    }
}
