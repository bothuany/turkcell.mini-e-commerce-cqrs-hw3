package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

import java.math.BigDecimal;
import java.util.List;

public record GetFilteredProductsQuery(
                String categoryId,
                BigDecimal minPrice,
                BigDecimal maxPrice,
                Boolean inStock) implements Command<List<ProductListingDto>> {

}
