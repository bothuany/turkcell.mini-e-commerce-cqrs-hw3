package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;

public record ResetCartCommand(Integer cartId) implements Command<Void> {
}
