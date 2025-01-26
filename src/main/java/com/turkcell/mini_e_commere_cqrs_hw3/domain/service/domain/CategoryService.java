package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getByIdWithDetails(Integer id);
    Category getById(Integer id);
    List<Category> getAllSubCategories(Integer parentId);
}
