package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall;

import an.awesome.pipelinr.Command;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CategoryService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetCategoryTreeQueryHandler implements Command.Handler<GetCategoryTreeQuery, List<CategoryListingDto>> {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryListingDto> handle(GetCategoryTreeQuery getCategoryTreeQuery) {
        List<Category> allCategories = categoryService.getAll();

        // Get root categories (categories without parent)
        List<Category> rootCategories = allCategories.stream()
                .filter(category -> category.getParent() == null)
                .toList();

        // Convert to DTOs with recursive subcategory mapping
        return rootCategories.stream()
                .map(this::mapCategoryToTreeDto)
                .collect(Collectors.toList());
    }

    private CategoryListingDto mapCategoryToTreeDto(Category category) {
        CategoryListingDto dto = modelMapper.map(category, CategoryListingDto.class);

        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            dto.setSubCategories(
                    category.getSubCategories().stream()
                            .map(this::mapCategoryToTreeDto)
                            .collect(Collectors.toList()));
        } else {
            dto.setSubCategories(new ArrayList<>());
        }

        dto.setProductCount(category.getProducts() != null ? category.getProducts().size() : 0);

        if (category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
            dto.setParentName(category.getParent().getName());
        }

        return dto;
    }
}
