package com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;

import java.util.List;

public record GetSubCategoriesQuery(Integer id) implements Command<List<CategoryListingDto>> {
}
