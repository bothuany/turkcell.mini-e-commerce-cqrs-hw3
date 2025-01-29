package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.CartItem;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Product;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.ProductRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.CartBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddToCartCommandHandler implements Command.Handler<AddToCartCommand, Void> {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartBusinessRules cartBusinessRules;
    private final ProductBusinessRules productBusinessRules;

    @Override
    public Void handle(AddToCartCommand command) {
        int cartId = cartRepository.findByUserId(command.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found")).getId();
        int productId = command.getProductId();
        int quantity = command.getQuantity();

        cartBusinessRules.cartIdMustExist(cartId);
        productBusinessRules.productIdMustExist(productId);
        productBusinessRules.productMustBeInStock(productId, quantity);
        productBusinessRules.productMustBeInStockInCart(cartId, productId, quantity);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Check if product already exists in cart
        CartItem existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        BigDecimal itemPrice = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));

        if (existingItem != null) {
            // Update existing cart item
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setPrice(existingItem.getPrice().add(itemPrice));
        } else {
            // Create new cart item
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            newCartItem.setPrice(itemPrice);
            cart.getCartItems().add(newCartItem);
        }

        // Update cart total
        cart.setTotalPrice((cart).getTotalPrice()
                .add(itemPrice));

        cartRepository.save(cart);
        return null;
    }
}
