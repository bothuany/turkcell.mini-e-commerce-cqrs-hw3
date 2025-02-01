package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CategoryBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryBusinessRules categoryBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public void add(Product product) {
        categoryBusinessRules.categoryMustExist(product.getCategory().getId());
        Product productWithSameName = productRepository
                .findByName(product.getName())
                .orElse(null);

        if (productWithSameName != null)
            throw new BusinessException("Product already exists");

        productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        categoryBusinessRules.categoryMustExist(product.getCategory().getId());
        productBusinessRules.productMustBeUniqueExceptForItself(product.getName(), product.getId());
        productBusinessRules.productIdMustExist(product.getId());

        return productRepository.save(product);
    }

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

    @Override
    public void delete(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));

        productRepository.delete(product);
    }
}
