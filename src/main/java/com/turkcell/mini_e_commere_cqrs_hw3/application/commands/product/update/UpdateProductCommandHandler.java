package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.ProductService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProductCommandHandler implements Command.Handler<UpdateProductCommand, ProductListingDto> {
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public ProductListingDto handle(UpdateProductCommand updateProductCommand) {
        productBusinessRules.productIdMustExist(updateProductCommand.getId());
        categoryBusinessRules.categoryMustExist(updateProductCommand.getCategoryId());

        Product product = productService.update(modelMapper.map(updateProductCommand, Product.class));
        return modelMapper.map(product, ProductListingDto.class);
    }
}
