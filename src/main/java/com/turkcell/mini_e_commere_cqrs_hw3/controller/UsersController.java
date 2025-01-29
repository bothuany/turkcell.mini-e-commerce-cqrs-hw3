package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.delete.DeleteUserCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getall.GetAllUsersQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.user.getbyid.GetUserByIdQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.UserListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.user.update.UpdateUserCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController extends BaseController {

    public UsersController(Pipeline pipeline ) {
        super(pipeline);
    }

    @GetMapping
    public ResponseEntity<List<UserListingDto>> getAll() {
        GetAllUsersQuery query = new GetAllUsersQuery();
        return ResponseEntity.ok(query.execute(pipeline));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserListingDto> getById(@PathVariable UUID id) {
        GetUserByIdQuery query = new GetUserByIdQuery(id);
        return ResponseEntity.ok(query.execute(pipeline));
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
