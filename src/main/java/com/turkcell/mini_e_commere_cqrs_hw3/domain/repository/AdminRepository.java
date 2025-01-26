package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
