package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CategoryService;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryCommandHandler implements Command.Handler<CreateCategoryCommand, Void> {
    private final CategoryService categoryService;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public Void handle(CreateCategoryCommand createCategoryCommand) {
        categoryBusinessRules.categoryNameMustBeUnique(createCategoryCommand.getName());
        if (createCategoryCommand.getParentId() == null) {
            Category category = modelMapper.map(createCategoryCommand, Category.class);
            categoryService.add(category);
        }
        else {
            categoryBusinessRules.categoryMustExist(createCategoryCommand.getParentId());
            categoryBusinessRules.categoryNameMustBeUnique(createCategoryCommand.getName());

            Category parentCategory = categoryService.getById(createCategoryCommand.getParentId());

            Category subCategory = new Category();
            subCategory.setName(createCategoryCommand.getName());
            subCategory.setParent(parentCategory);
            categoryService.add(subCategory);
        }
        return null;
    }
}
