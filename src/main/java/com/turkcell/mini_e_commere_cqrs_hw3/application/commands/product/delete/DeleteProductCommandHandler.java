package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProductCommandHandler implements Command.Handler<DeleteProductCommand, Void> {
    private final ProductRepository productRepository;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public Void handle(DeleteProductCommand deleteProductCommand) {
        productBusinessRules.productIdMustExist(deleteProductCommand.getId());
        productRepository.deleteById(deleteProductCommand.getId());
        return null;
    }
}
