package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall;

import an.awesome.pipelinr.Command;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CategoryService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllCategoriesQueryHandler implements Command.Handler<GetAllCategoriesQuery, List<CategoryListingDto>> {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryListingDto> handle(GetAllCategoriesQuery getAllCategoriesQuery) {
        return categoryService.getAll().stream()
                .map(category -> modelMapper.map(category, CategoryListingDto.class))
                .collect(Collectors.toList());
    }
}
