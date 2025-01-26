package com.turkcell.mini_e_commere_cqrs_hw3.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sellers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seller extends User {
    private String companyName;
}