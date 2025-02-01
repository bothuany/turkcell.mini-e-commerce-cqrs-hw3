package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.ProductService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductCommandHandler implements Command.Handler<CreateProductCommand, ProductListingDto> {
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public ProductListingDto handle(CreateProductCommand createProductCommand) {
        categoryBusinessRules.categoryMustExist(createProductCommand.getCategoryId());
        Product productWithSameName = productService.getByName(createProductCommand.getName());

        if (productWithSameName != null)
            throw new BusinessException("Product already exists");

        Product product = productService.update(modelMapper.map(createProductCommand, Product.class));
        return modelMapper.map(product, ProductListingDto.class);
    }
}
