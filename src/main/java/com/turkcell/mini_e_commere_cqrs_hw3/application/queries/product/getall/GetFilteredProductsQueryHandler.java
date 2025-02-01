package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getall;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.ProductService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

import an.awesome.pipelinr.Command;

@Component
@RequiredArgsConstructor
public class GetFilteredProductsQueryHandler
        implements Command.Handler<GetFilteredProductsQuery, List<ProductListingDto>> {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductListingDto> handle(GetFilteredProductsQuery getFilteredProductsQuery) {
        List<Product> products = productService.search(
                getFilteredProductsQuery.categoryId(),
                getFilteredProductsQuery.minPrice(),
                getFilteredProductsQuery.maxPrice(),
                getFilteredProductsQuery.inStock());

        return products.stream()
                .map(product -> modelMapper.map(product, ProductListingDto.class))
                .collect(Collectors.toList());
    }
}
