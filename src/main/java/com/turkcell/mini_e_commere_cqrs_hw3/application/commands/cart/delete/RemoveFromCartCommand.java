package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.delete;

import an.awesome.pipelinr.Command;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveFromCartCommand implements Command<Void> {
    @NotNull(message = "Product ID cannot be null")
    private Integer cartItemId;
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
    private UUID userId;
}
