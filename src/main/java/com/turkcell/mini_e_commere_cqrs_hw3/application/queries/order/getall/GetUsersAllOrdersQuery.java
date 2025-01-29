package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthenticatedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;

import java.util.List;

public record GetUsersAllOrdersQuery() implements Command<List<OrderListingDto>>, AuthenticatedRequest {
}
