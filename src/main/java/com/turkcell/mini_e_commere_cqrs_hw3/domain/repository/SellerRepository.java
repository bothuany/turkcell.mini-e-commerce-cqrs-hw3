package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
}

