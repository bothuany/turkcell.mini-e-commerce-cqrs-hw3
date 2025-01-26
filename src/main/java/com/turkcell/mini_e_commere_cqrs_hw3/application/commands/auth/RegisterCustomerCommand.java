package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCustomerCommand extends RegisterCommand {
    @NotNull(message = "Address cannot be null")
    private String address;
}
