package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

public record GetProductByIdQuery(Integer id) implements Command<ProductListingDto> {
}
