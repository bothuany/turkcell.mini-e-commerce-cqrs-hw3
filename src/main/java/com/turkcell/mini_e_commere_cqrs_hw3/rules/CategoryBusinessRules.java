package com.turkcell.mini_e_commere_cqrs_hw3.rules;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public void categoryMustExist(Integer id) {
        categoryRepository.findById(id).orElseThrow(() -> new BusinessException("Category not found"));
    }

    public void categoryNameMustBeUnique(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new BusinessException("Category name must be unique.");
        }
    }

    public void categoryNameMustBeUniqueExceptForItself(Integer id, String name) {
        if (categoryRepository.existsByNameAndIdNot(name, id)) {
            throw new BusinessException("Category name must be unique.");
        }
    }

    public void categoryMustNotHaveAssociatedProducts(Integer categoryId) {
        if (productRepository.existsByCategoryId(categoryId)) {
            throw new BusinessException("Category cannot be deleted because it has associated products.");
        }
    }

    public void categoryMustNotHaveSubCategories(Integer categoryId) {
        if (categoryRepository.existsByParentId(categoryId)) {
            throw new BusinessException("Category cannot be deleted because it has sub-categories.");
        }
    }
}
