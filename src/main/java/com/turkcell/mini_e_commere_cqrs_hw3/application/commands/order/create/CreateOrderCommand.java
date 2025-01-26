package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand implements Command<OrderListingDto> {
    private UUID userId;
}
