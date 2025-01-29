package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall;

import an.awesome.pipelinr.Command;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetSubCategoriesQueryHandler implements Command.Handler<GetSubCategoriesQuery, List<CategoryListingDto>> {
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryListingDto> handle(GetSubCategoriesQuery getSubCategoriesQuery) {
        Integer parentId = getSubCategoriesQuery.id();
        categoryBusinessRules.categoryMustExist(parentId);
        List<Category> subCategories = categoryRepository.findAllByParentId(parentId);
        return subCategories.stream()
                .map(category -> modelMapper.map(category, CategoryListingDto.class))
                .collect(Collectors.toList());
    }
}
