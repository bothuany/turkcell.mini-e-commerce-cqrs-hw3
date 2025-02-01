package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.update;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CategoryService;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCategoryCommandHandler implements Command.Handler<UpdateCategoryCommand, Void> {
    private final CategoryService categoryService;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public Void handle(UpdateCategoryCommand updateCategoryCommand) {
        categoryBusinessRules.categoryMustExist(updateCategoryCommand.getId());

        Category categoryToUpdate = categoryService.getById(updateCategoryCommand.getId());

        categoryBusinessRules.categoryNameMustBeUniqueExceptForItself(updateCategoryCommand.getId(), updateCategoryCommand.getName());

        categoryToUpdate.setName(updateCategoryCommand.getName());
        if (updateCategoryCommand.getParentId() != null) {
            categoryBusinessRules.categoryMustExist(updateCategoryCommand.getParentId());
            if (updateCategoryCommand.getId().equals(updateCategoryCommand.getParentId())) {
                throw new BusinessException("A category cannot be its own parent");
            }
            Category parent = categoryService.getById(updateCategoryCommand.getParentId());
            categoryToUpdate.setParent(parent);
        }

        categoryService.update(categoryToUpdate);
        return null;
    }
}
