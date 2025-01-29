package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getbyid;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

import an.awesome.pipelinr.Command;

@Component
@RequiredArgsConstructor
public class GetProductByIdQueryHandler implements Command.Handler<GetProductByIdQuery, ProductListingDto> {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductListingDto handle(GetProductByIdQuery getProductByIdQuery) {
        Product product = productRepository.findById(getProductByIdQuery.id())
                .orElseThrow(() -> new BusinessException("Product not found"));
        return modelMapper.map(product, ProductListingDto.class);
    }
}
