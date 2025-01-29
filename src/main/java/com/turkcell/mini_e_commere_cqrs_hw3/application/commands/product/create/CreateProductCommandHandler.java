package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public ProductListingDto handle(CreateProductCommand createProductCommand) {
        categoryBusinessRules.categoryMustExist(createProductCommand.getCategoryId());
        Product productWithSameName = productRepository
                .findByName(createProductCommand.getName())
                .orElse(null);

        if (productWithSameName != null)
            throw new BusinessException("Product already exists");

        Product product = productRepository.save(modelMapper.map(createProductCommand, Product.class));
        return modelMapper.map(product, ProductListingDto.class);
    }
}
