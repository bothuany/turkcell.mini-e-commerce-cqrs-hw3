package com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth;

import java.util.List;

public interface AuthorizedRequest {
    List<String> getRequiredRoles();
}
