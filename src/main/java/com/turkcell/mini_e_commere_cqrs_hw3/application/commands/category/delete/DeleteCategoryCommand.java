package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.category.delete;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCategoryCommand implements Command<Void> {
    private int id;
}
