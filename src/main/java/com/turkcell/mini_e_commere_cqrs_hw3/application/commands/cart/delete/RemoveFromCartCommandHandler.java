package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartItemRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CartBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RemoveFromCartCommandHandler implements Command.Handler<RemoveFromCartCommand, Void> {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartBusinessRules cartBusinessRules;
    private final ProductBusinessRules productBusinessRules;
    @Override
    public Void handle(RemoveFromCartCommand command) {
        int cartId = cartRepository.findByUserId(command.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found")).getId();
        int cartItemId = command.getCartItemId();
        int quantity = command.getQuantity();

        cartBusinessRules.cartIdMustExist(cartId);
        cartBusinessRules.cartItemMustExist(cartItemId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        productBusinessRules.productIdMustExist(cartItem.getProduct().getId());

        if (cartItem.getQuantity() <= quantity) {
            // Remove the entire cart item
            cart.getCartItems().remove(cartItem);
            // Update cart total
            cart.setTotalPrice(cart.getTotalPrice()
                    .subtract(cartItem.getProduct().getUnitPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))));
            cartItemRepository.deleteById(cartItem.getId());
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

        cartRepository.save(cart);

        return null;
    }
}
