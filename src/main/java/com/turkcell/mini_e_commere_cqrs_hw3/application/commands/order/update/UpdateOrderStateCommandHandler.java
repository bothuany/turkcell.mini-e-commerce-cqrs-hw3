package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OrderRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OrderStatus;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class UpdateOrderStateCommandHandler implements Command.Handler<UpdateOrderStateCommand, OrderListingDto> {
    private static final LinkedList<OrderStatus> ORDER_STATUS_PROGRESSION = new LinkedList<>();

    static {
        ORDER_STATUS_PROGRESSION.add(OrderStatus.PREPARING);
        ORDER_STATUS_PROGRESSION.add(OrderStatus.SHIPPED);
        ORDER_STATUS_PROGRESSION.add(OrderStatus.DELIVERED);
    }

    private final OrderRepository orderRepository;
    private final OrderBusinessRules orderBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public OrderListingDto handle(UpdateOrderStateCommand updateOrderStateCommand) {
        orderBusinessRules.checkOrderExist(updateOrderStateCommand.getOrderId());
        Order order = orderRepository.findById(updateOrderStateCommand.getOrderId()).orElse(null);
        OrderStatus currentStatus = order.getStatus();
        handleOrderStatusTransition(currentStatus, order);
        orderRepository.save(order);

        return modelMapper.map(order, OrderListingDto.class);
    }

    private void handleOrderStatusTransition(OrderStatus currentStatus, Order order) {
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
}
