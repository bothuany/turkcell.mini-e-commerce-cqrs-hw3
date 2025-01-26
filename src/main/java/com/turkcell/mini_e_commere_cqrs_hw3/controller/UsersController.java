package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.delete.DeleteUserCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.update.UpdateUserCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController extends BaseController {
    private final UserApplicationService userApplicationService;

    public UsersController(Pipeline pipeline, UserApplicationService userApplicationService) {
        super(pipeline);
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<UserListingDto>> getAll() {
        return ResponseEntity.ok(this.userApplicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserListingDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.userApplicationService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(id);
        deleteUserCommand.execute(pipeline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserListingDto> update(@PathVariable UUID id, @RequestBody UpdateUserCommand updateUserCommand) {
        updateUserCommand.setId(id);
        updateUserCommand.execute(pipeline);

        return ResponseEntity.ok(updateUserCommand.execute(pipeline));
    }
}
