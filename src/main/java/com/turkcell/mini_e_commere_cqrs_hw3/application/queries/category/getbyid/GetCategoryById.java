package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;

public record GetCategoryById(Integer id) implements Command<CategoryListingDto> {
}
