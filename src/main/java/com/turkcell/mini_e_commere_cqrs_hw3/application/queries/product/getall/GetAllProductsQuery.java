package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

import java.util.List;

public record GetAllProductsQuery() implements Command<List<ProductListingDto>> {
}
