package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);
    void update(Category category);
    void delete(Integer id);
    List<Category> getAll();
    Category getByIdWithDetails(Integer id);
    Category getById(Integer id);
    Category getByName(String name);
    List<Category> getAllSubCategories(Integer parentId);
}
