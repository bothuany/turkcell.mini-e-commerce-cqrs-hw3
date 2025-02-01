package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth.LoginCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth.RegisterAdminCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth.RegisterCustomerCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth.RegisterSellerCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
    public AuthController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping("register/customer")
    public ResponseEntity<AuthUserDto> register(@RequestBody RegisterCustomerCommand registerCustomerCommand) {
        return ResponseEntity.ok(registerCustomerCommand.execute(pipeline));
    }

    @PostMapping("register/admin")
    public ResponseEntity<AuthUserDto> registerAdmin(@RequestBody RegisterAdminCommand registerAdminCommand) {
        return ResponseEntity.ok(registerAdminCommand.execute(pipeline));
    }

    @PostMapping("register/seller")
    public ResponseEntity<AuthUserDto> registerSeller(@RequestBody RegisterSellerCommand registerSellerDto) {
        return ResponseEntity.ok(registerSellerDto.execute(pipeline));
    }

    @PostMapping("login")
    public ResponseEntity<AuthUserDto> login(@RequestBody LoginCommand loginCommand) {
        return ResponseEntity.ok(loginCommand.execute(pipeline));
    }
}