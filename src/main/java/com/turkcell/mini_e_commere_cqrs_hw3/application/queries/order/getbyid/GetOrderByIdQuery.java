package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthenticatedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;

public record GetOrderByIdQuery(Integer id) implements Command<OrderListingDto>, AuthenticatedRequest {
}
