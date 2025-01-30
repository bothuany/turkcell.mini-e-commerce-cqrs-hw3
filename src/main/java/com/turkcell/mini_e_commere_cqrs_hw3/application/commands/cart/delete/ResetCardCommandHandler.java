package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ResetCardCommandHandler implements Command.Handler<ResetCartCommand, Void> {
    private final CartRepository cartRepository;

    @Override
    public Void handle(ResetCartCommand resetCartCommand) {
        Cart cart = cartRepository.findById(resetCartCommand.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartRepository.save(cart);
        return null;
    }
}
