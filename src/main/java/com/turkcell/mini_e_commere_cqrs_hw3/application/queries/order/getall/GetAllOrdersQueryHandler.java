package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OrderService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetAllOrdersQueryHandler implements Command.Handler<GetUsersAllOrdersQuery, List<OrderListingDto>>{
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderListingDto> handle(GetUsersAllOrdersQuery getUsersAllOrdersQuery) {
        UUID userId = UUID.fromString(getUsersAllOrdersQuery.userId());

        return orderService.getAllUserOrders(userId).stream()
                .map(order -> modelMapper.map(order, OrderListingDto.class))
                .collect(java.util.stream.Collectors.toList());
    }
}
