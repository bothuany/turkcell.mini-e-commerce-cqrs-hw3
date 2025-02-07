package com.turkcell.mini_e_commere_cqrs_hw3.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListingDto {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private List<CategoryListingDto> subCategories;
    private int productCount;
}
