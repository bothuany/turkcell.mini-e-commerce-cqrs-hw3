package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Order;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OrderItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.OrderRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.UserRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OrderStatus;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements Command.Handler<CreateOrderCommand, OrderListingDto>{
    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderBusinessRules orderBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public OrderListingDto handle(CreateOrderCommand createOrderCommand) {
        User user = userRepository.findById(createOrderCommand.getUserId())
                .orElseThrow(() -> new BusinessException("User not found"));
        orderBusinessRules.cartMustNotBeEmpty(user.getCart());
        orderBusinessRules.checkTheProductStockAfterUpdateProductStockForOrder(user.getCart());

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PREPARING);
        order.setTotalPrice(user.getCart().getTotalPrice());

        List<OrderItem> orderItems = user.getCart().getCartItems().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        cartService.resetCart(user.getCart().getId());
        orderRepository.save(order);

        return modelMapper.map(order, OrderListingDto.class);
    }
}
