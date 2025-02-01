package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OrderItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartService;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements Command.Handler<CreateOrderCommand, OrderListingDto>{
    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderBusinessRules orderBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public OrderListingDto handle(CreateOrderCommand createOrderCommand) {
        User user = userService.getById(createOrderCommand.getUserId());
        orderBusinessRules.cartMustNotBeEmpty(user.getCart());
        orderBusinessRules.checkTheProductStockAfterUpdateProductStockForOrder(user.getCart());

        Order order = orderService.createOrderForUser(user);
        List<OrderItem> orderItems = orderService.createOrderItems(user.getCart(), order);
        order.setOrderItems(orderItems);

        cartService.resetCart(user.getCart().getId());
        orderService.update(order);

        return modelMapper.map(order, OrderListingDto.class);
    }
}
