package com.turkcell.mini_e_commere_cqrs_hw3.domain.service;

import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.OperationClaim;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static Map<String, Object> getRoles(User user) {
        Map<String, Object> roles = new HashMap<>();
        roles.put("roles", user.getOperationClaims().stream().map(OperationClaim::getName).toList());
        return roles;
    }
}
