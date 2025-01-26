package com.turkcell.mini_e_commere_cqrs_hw3.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {
    private UUID id;
    private String name;
    private String surname;
    private String username;
    private String companyName;
}
