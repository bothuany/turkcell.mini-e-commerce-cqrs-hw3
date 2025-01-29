package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryCommandHandler implements Command.Handler<CreateCategoryCommand, Void> {
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public Void handle(CreateCategoryCommand createCategoryCommand) {
        categoryBusinessRules.categoryNameMustBeUnique(createCategoryCommand.getName());
        if (createCategoryCommand.getParentId() == null) {
            Category category = modelMapper.map(createCategoryCommand, Category.class);
            categoryRepository.save(category);
        }
        else {
            categoryBusinessRules.categoryMustExist(createCategoryCommand.getParentId());

            Category parentCategory = categoryRepository.findById(createCategoryCommand.getParentId())
                    .orElseThrow(() -> new BusinessException("Parent category not found"));
            Category subCategory = categoryRepository.findByName(createCategoryCommand.getName())
                    .orElseThrow(() -> new BusinessException("Category with this name already exists"));
            if (subCategory == null) {
                throw new BusinessException("Category with this name already exists");
            }

            subCategory.setParent(parentCategory);
            categoryRepository.save(subCategory);
        }
        return null;
    }
}
