package com.turkcell.mini_e_commere_cqrs_hw3.core.pipelines.validation;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ValidationBehavior implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        //TODO Validation logic here

        return next.invoke();
    }
}
