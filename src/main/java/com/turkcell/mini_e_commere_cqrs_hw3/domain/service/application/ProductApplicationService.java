package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductApplicationService {

    List<ProductListingDto> getAll();

    List<ProductListingDto> search(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean inStock);

    ProductListingDto findById(Integer id);

}
