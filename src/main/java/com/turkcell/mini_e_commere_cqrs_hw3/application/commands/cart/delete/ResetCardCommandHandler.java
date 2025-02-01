package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Cart;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ResetCardCommandHandler implements Command.Handler<ResetCartCommand, Void> {
    private final CartService cartService;

    @Override
    public Void handle(ResetCartCommand resetCartCommand) {
        Cart cart = cartService.getById(resetCartCommand.cartId());

        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartService.update(cart);
        return null;
    }
}
