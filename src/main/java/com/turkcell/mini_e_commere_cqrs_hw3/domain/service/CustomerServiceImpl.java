package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Customer;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}