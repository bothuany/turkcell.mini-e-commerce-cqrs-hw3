package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOrderCommandHandler implements Command.Handler<DeleteOrderCommand, Void>{
    private final OrderService orderService;
    private final OrderBusinessRules orderBusinessRules;

    @Override
    public Void handle(DeleteOrderCommand deleteOrderCommand) {
        orderBusinessRules.checkOrderExist(deleteOrderCommand.getOrderId());
        orderService.delete(deleteOrderCommand.getOrderId());
        return null;
    }
}
