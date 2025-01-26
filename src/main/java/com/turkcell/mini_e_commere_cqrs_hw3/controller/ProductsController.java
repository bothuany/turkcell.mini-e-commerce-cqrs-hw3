package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.create.CreateProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.delete.DeleteProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.product.update.UpdateProductCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.product.ProductListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application.ProductApplicationService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController()
@RequestMapping("/api/v1/products")
public class ProductsController extends BaseController {
    private final ProductApplicationService productApplicationService;

    public ProductsController(Pipeline pipeline, ProductApplicationService productApplicationService) {
        super(pipeline);
        this.productApplicationService = productApplicationService;
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
        return ResponseEntity.ok(this.productApplicationService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductListingDto>> search(@RequestParam @Nullable String categoryId,
                                                          @RequestParam @Nullable BigDecimal minPrice,
                                                          @RequestParam @Nullable BigDecimal maxPrice,
                                                          @RequestParam @Nullable Boolean inStock) {
        return ResponseEntity.ok(this.productApplicationService.search(categoryId, minPrice, maxPrice, inStock));
    }
}
