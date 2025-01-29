package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.create.CreateProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.delete.DeleteProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.update.UpdateProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getall.GetAllProductsQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getall.GetFilteredProductsQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.product.getbyid.GetProductByIdQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController()
@RequestMapping("/api/v1/products")
public class ProductsController extends BaseController {

    public ProductsController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping
    public ResponseEntity<ProductListingDto> add(@RequestBody @Valid CreateProductCommand createProductCommand) {
        return ResponseEntity.ok(createProductCommand.execute(pipeline));
    }

    @PutMapping
    public ResponseEntity<ProductListingDto> update(@RequestBody @Valid UpdateProductCommand updateProductCommand) {
        return ResponseEntity.ok(updateProductCommand.execute(pipeline));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(id);
        deleteProductCommand.execute(pipeline);
    }

    @GetMapping
    public ResponseEntity<List<ProductListingDto>> getAll() {
        GetAllProductsQuery query = new GetAllProductsQuery();
        return ResponseEntity.ok(query.execute(pipeline));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductListingDto> getById(@PathVariable Integer id) {
        GetProductByIdQuery query = new GetProductByIdQuery(id);
        return ResponseEntity.ok(query.execute(pipeline));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductListingDto>> search(@RequestParam @Nullable String categoryId,
                                                          @RequestParam @Nullable BigDecimal minPrice,
                                                          @RequestParam @Nullable BigDecimal maxPrice,
                                                          @RequestParam @Nullable Boolean inStock) {
        GetFilteredProductsQuery query = new GetFilteredProductsQuery(categoryId, minPrice, maxPrice, inStock);
        return ResponseEntity.ok(query.execute(pipeline));
    }
}
