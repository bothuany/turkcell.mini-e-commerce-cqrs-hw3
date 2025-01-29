package com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth;

import an.awesome.pipelinr.Command;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Component
@Order(2)
public class AuthorizationBehavior implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof AuthorizedRequest) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                throw new RuntimeException("Authentication required");
            }

            boolean hasRequiredRoles = auth
                    .getAuthorities()
                    .stream()
                    .anyMatch(
                            role -> ((AuthorizedRequest) c)
                                    .getRequiredRoles()
                                    .stream()
                                    .anyMatch(req -> req.equalsIgnoreCase(role.getAuthority()))
                    );
            if (!hasRequiredRoles)
                throw new RuntimeException("You dont have the required roles");
        }

        return next.invoke();
    }
}
