package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.create.AddToCartCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete.RemoveFromCartCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getall.GetAllCartsQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid.GetByIdCartQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.cart.getbyid.GetMyCartQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/carts")
public class CartController extends BaseController {

    public CartController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCartItemToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AddToCartCommand addToCartCommand) {
        addToCartCommand.setUserId(UUID.fromString(userDetails.getUsername()));
        addToCartCommand.execute(pipeline);
    }

    @DeleteMapping()
    public void removeCartItemFromCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RemoveFromCartCommand removeFromCartCommand) {
        removeFromCartCommand.setUserId(UUID.fromString(userDetails.getUsername()));
        removeFromCartCommand.execute(pipeline);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartListingDto>> getAll() {
        GetAllCartsQuery getAllCartsQuery = new GetAllCartsQuery();
        return ResponseEntity.ok(getAllCartsQuery.execute(pipeline));
    }

    @GetMapping()
    public ResponseEntity<CartListingDto> getMyCart(@AuthenticationPrincipal UserDetails userDetails) {
        GetMyCartQuery getMyCartQuery = new GetMyCartQuery(UUID.fromString(userDetails.getUsername()));
        return ResponseEntity.ok(getMyCartQuery.execute(pipeline));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartListingDto> getProducts(@PathVariable Integer cartId) {
        GetByIdCartQuery getByIdCartQuery = new GetByIdCartQuery(cartId);
        return ResponseEntity.ok(getByIdCartQuery.execute(pipeline));
    }
}
