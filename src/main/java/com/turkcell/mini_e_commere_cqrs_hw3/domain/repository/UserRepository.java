package com.turkcell.mini_e_commere_cqrs_hw3.domain.repository;


import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String username);

}
