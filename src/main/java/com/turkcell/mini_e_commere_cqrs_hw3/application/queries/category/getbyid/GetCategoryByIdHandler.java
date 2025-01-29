package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getbyid;

import an.awesome.pipelinr.Command;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCategoryByIdHandler implements Command.Handler<GetCategoryById, CategoryListingDto> {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryListingDto handle(GetCategoryById getCategoryById) {
        Integer id = getCategoryById.id();
        Category category = categoryRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new BusinessException("Category not found"));
        return modelMapper.map(category, CategoryListingDto.class);
    }
}
