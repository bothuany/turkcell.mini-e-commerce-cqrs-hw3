package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.cart.create;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth.AuthorizedRequest;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartCommand implements Command<Void>, AuthorizedRequest {
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
    private UUID userId;

    @Override
    public List<String> getRequiredRoles() {
        return List.of(OperationClaims.customer.name());
    }
}
