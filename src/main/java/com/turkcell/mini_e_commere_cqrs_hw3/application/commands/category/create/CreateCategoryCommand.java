package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.create;

import an.awesome.pipelinr.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryCommand implements Command<Void> {
    @NotBlank(message = "Category name cannot be empty")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;

    private Integer parentId;
}
