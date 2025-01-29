package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public ProductListingDto handle(UpdateProductCommand updateProductCommand) {
        productBusinessRules.productIdMustExist(updateProductCommand.getId());
        categoryBusinessRules.categoryMustExist(updateProductCommand.getCategoryId());

        Product productWithSameName = productRepository
                .findByNameIsAndIdIsNot(updateProductCommand.getName(), updateProductCommand.getId())
                .orElse(null);

        if (productWithSameName != null)
            throw new BusinessException("Product already exists");

        Product productToUpdate = productRepository.findById(updateProductCommand.getId())
                .orElseThrow(() -> new BusinessException("Product not found"));

        Product product = productRepository.save(productToUpdate);
        return modelMapper.map(product, ProductListingDto.class);
    }
}
