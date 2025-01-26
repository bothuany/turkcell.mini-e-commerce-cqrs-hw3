package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStateCommand implements Command<OrderListingDto> {
    private int orderId;
}
