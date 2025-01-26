package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getById(Integer id);
    Product getByName(String name);
    List<Product> getAll();
    List<Product> search(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean inStock);
}
