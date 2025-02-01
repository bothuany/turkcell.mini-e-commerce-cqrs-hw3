package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartItemService;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartService;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CartBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RemoveFromCartCommandHandler implements Command.Handler<RemoveFromCartCommand, Void> {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CartBusinessRules cartBusinessRules;
    private final ProductBusinessRules productBusinessRules;
    @Override
    public Void handle(RemoveFromCartCommand command) {
        int cartId = cartService.getCartIdByUserId(command.getUserId());
        int cartItemId = command.getCartItemId();
        int quantity = command.getQuantity();

        cartBusinessRules.cartIdMustExist(cartId);
        cartBusinessRules.cartItemMustExist(cartItemId);

        Cart cart = cartService.getById(cartId);

        CartItem cartItem = cartItemService.getById(cartItemId);

        productBusinessRules.productIdMustExist(cartItem.getProduct().getId());

        if (cartItem.getQuantity() <= quantity) {
            // Remove the entire cart item
            cart.getCartItems().remove(cartItem);
            // Update cart total
            cart.setTotalPrice(cart.getTotalPrice()
                    .subtract(cartItem.getProduct().getUnitPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))));
            cartItemService.delete(cartItem.getId());
        } else {
            // Reduce the quantity
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            BigDecimal priceReduction = cartItem.getProduct().getUnitPrice()
                    .multiply(BigDecimal.valueOf(quantity));
            cartItem.setPrice(cartItem.getPrice().subtract(priceReduction));
            // Update cart total
            cart.setTotalPrice(cart.getTotalPrice()
                    .subtract(cartItem.getProduct().getUnitPrice()
                            .multiply(BigDecimal.valueOf(quantity))));
        }

        cartService.update(cart);

        return null;
    }
}
