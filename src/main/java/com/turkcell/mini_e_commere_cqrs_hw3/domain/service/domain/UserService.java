package com.turkcell.mini_e_commere_cqrs_hw3.domain.service.domain;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    User create(User user);

    User getById(UUID userId);

    User getByUsername(String username);

    List<User> getAll();

    User getActiveUser();

    UUID getActiveUserId();

    static Map<String, Object> getRoles(User user) {
        Map<String, Object> roles = new HashMap<>();
        roles.put("roles", user.getOperationClaims().stream().map(OperationClaim::getName).toList());
        return roles;
    }
}
