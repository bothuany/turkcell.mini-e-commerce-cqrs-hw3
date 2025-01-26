package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.util.exception.type.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> search(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean inStock) {
        return productRepository.search(categoryId, minPrice, maxPrice, inStock);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("Product not found"));
    }
}
