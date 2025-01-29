package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllOrdersQueryHandler implements Command.Handler<GetUsersAllOrdersQuery, List<OrderListingDto>>{
    @Override
    public List<OrderListingDto> handle(GetUsersAllOrdersQuery getUsersAllOrdersQuery) {
        return List.of();
    }
}
