package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductApplicationServiceImpl implements ProductApplicationService {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductListingDto> getAll() {
        List<Product> products = productService.getAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductListingDto> search(
            String categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Boolean inStock) {
        List<Product> products = productService.search(categoryId, minPrice, maxPrice, inStock);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductListingDto findById(Integer id) {
        Product product = productService.getById(id);
        return modelMapper.map(product, ProductListingDto.class);
    }
}
