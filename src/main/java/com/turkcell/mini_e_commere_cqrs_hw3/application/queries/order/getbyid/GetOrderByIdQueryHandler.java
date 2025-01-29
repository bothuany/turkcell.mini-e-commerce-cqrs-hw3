package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrderByIdQueryHandler  implements Command.Handler<GetOrderByIdQuery, OrderListingDto>{
    @Override
    public OrderListingDto handle(GetOrderByIdQuery getOrderByIdQuery) {
        return null;
    }
}
