package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.delete.DeleteCategoryCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall.GetAllCategoriesQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall.GetCategoryTreeQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getall.GetSubCategoriesQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.category.getbyid.GetCategoryById;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.category.CategoryListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.create.CreateCategoryCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.update.UpdateCategoryCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {
    public CategoryController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CreateCategoryCommand createCategoryCommand) {
        createCategoryCommand.execute(pipeline);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoryCommand updateCategoryCommand) {
        updateCategoryCommand.setId(id);
        updateCategoryCommand.execute(pipeline);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(id);
        deleteCategoryCommand.execute(pipeline);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryListingDto>> getAllCategories() {
        GetAllCategoriesQuery query = new GetAllCategoriesQuery();
        return ResponseEntity.ok(query.execute(pipeline));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryListingDto> getCategoryById(@PathVariable Integer id) {
        GetCategoryById query = new GetCategoryById(id);
        return ResponseEntity.ok(query.execute(pipeline));
    }

    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryListingDto>> getSubCategories(@PathVariable Integer id) {
        GetSubCategoriesQuery query = new GetSubCategoriesQuery(id);
        return ResponseEntity.ok(query.execute(pipeline));
    }

    @GetMapping("/tree")
    public ResponseEntity<List<CategoryListingDto>> getCategoryTree() {
        GetCategoryTreeQuery query = new GetCategoryTreeQuery();
        return ResponseEntity.ok(query.execute(pipeline));
    }
}
