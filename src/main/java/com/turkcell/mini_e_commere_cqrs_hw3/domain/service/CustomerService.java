package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(UUID customerId);
}
