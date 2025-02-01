package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrderByIdQueryHandler  implements Command.Handler<GetOrderByIdQuery, OrderListingDto>{
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Override
    public OrderListingDto handle(GetOrderByIdQuery getOrderByIdQuery) {
        return modelMapper.map(orderService.getById(getOrderByIdQuery.id()), OrderListingDto.class);
    }
}
