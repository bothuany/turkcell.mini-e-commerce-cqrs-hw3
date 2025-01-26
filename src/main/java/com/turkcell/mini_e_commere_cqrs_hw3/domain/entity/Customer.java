package com.turkcell.mini_e_commere_cqrs_hw3.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User{
    private String address;
}
