package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Category;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CategoryRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
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
    @Transactional
    public void add(Category category) {
        categoryBusinessRules.categoryNameMustBeUnique(category.getName());
        if (category.getParent() != null) {
            categoryBusinessRules.categoryMustExist(category.getParent().getId());
        }

        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void update(Category category) {
        categoryBusinessRules.categoryMustExist(category.getId());

        Category categoryToUpdate = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new BusinessException("Category not found"));

        if (!categoryToUpdate.getName().equals(category.getName())) {
            if (categoryRepository.findByName(category.getName()).isPresent()) {
                throw new BusinessException("Category with this name already exists");
            }
        }

        categoryToUpdate.setName(category.getName());
        if (category.getParent() != null) {
            categoryBusinessRules.categoryMustExist(category.getParent().getId());
            if (category.getId().equals(category.getParent().getId())) {
                throw new BusinessException("A category cannot be its own parent");
            }
            categoryToUpdate.setParent(category.getParent());
        }

        categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoryBusinessRules.categoryMustExist(id);
        categoryBusinessRules.categoryMustNotHaveAssociatedProducts(id);
        categoryBusinessRules.categoryMustNotHaveSubCategories(id);

        categoryRepository.deleteById(id);
    }

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
    public Category getByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("Category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllSubCategories(Integer parentId) {
        categoryBusinessRules.categoryMustExist(parentId);
        return categoryRepository.findAllByParentId(parentId);
    }
}
