package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Admin;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }
}
