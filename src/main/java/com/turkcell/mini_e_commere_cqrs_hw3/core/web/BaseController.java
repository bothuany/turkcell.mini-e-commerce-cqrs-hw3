package com.turkcell.mini_e_commere_cqrs_hw3.core.web;


import an.awesome.pipelinr.Pipeline;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseController {
    protected final Pipeline pipeline;
}
