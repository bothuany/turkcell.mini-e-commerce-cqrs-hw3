package com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.auth;

import an.awesome.pipelinr.Command;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Component
@Order(1)
public class AuthenticationBehavior implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof AuthenticatedRequest || c instanceof AuthorizedRequest) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) {
                throw new RuntimeException("Authentication required");
            }
        }
        return next.invoke();
    }
}
