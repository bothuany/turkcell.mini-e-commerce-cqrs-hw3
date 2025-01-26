package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.create.AddToCartCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete.RemoveFromCartCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application.CartApplicationService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.cart.CartListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.AuthUserDto;
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
    private final CartApplicationService cartApplicationService;

    public CartController(Pipeline pipeline, CartApplicationService cartApplicationService) {
        super(pipeline);
        this.cartApplicationService = cartApplicationService;
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
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<CartListingDto>> getAll() {
        return ResponseEntity.ok(this.cartApplicationService.getAll());
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<CartListingDto> getMyCart() {
        return ResponseEntity.ok(this.cartApplicationService.getMyCart());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartListingDto> getProducts(@PathVariable Integer cartId) {
        return ResponseEntity.ok(this.cartApplicationService.getById(cartId));
    }
}
