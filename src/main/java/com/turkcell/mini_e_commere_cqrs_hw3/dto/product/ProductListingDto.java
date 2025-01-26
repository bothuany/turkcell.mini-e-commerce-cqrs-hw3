package com.turkcell.mini_e_commere_cqrs_hw3.dto.product;

import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.SellerDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListingDto {
    private Integer id;
    private String name;
    private BigDecimal unitPrice;
    private int stock;
    private Integer categoryId;
    private String categoryName;
    private SellerDto seller;
    private String description;
    private String image;
}
