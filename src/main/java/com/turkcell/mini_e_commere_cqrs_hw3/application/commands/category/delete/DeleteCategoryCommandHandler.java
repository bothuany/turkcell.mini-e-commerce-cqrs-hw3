package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCategoryCommandHandler implements Command.Handler<DeleteCategoryCommand, Void>{
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public Void handle(DeleteCategoryCommand deleteCategoryCommand) {
        int id = deleteCategoryCommand.getId();
        categoryBusinessRules.categoryMustExist(id);
        categoryBusinessRules.categoryMustNotHaveAssociatedProducts(id);
        categoryBusinessRules.categoryMustNotHaveSubCategories(id);

        categoryRepository.deleteById(id);
        return null;
    }
}
