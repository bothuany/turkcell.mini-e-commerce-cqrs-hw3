package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.create.CreateCategoryCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.update.UpdateCategoryCommand;

import java.util.List;

public interface CategoryApplicationService {
    List<CategoryListingDto> getAll();
    CategoryListingDto getById(Integer id);
    List<CategoryListingDto> getAllSubCategories(Integer parentId);
    List<CategoryListingDto> getCategoryTree();
}
