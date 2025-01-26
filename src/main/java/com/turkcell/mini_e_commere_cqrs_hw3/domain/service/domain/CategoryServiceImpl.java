package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getByIdWithDetails(Integer id) {
        return categoryRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new BusinessException("Category not found"));
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllSubCategories(Integer parentId) {
        categoryBusinessRules.categoryMustExist(parentId);
        return categoryRepository.findAllByParentId(parentId);
    }
}
