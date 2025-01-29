package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.*;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.AdminRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.CustomerRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.SellerRepository;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OperationClaimService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.AuthUserDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.UserBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService.getRoles;

@Component
@RequiredArgsConstructor
public class RegisterCommandHandler implements Command.Handler<RegisterCommand, AuthUserDto> {
    private final UserBusinessRules userBusinessRules;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final OperationClaimService operationClaimService;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Override
    public AuthUserDto handle(RegisterCommand registerCommand) {
        userBusinessRules.usernameMustNotExist(registerCommand.getUsername());

        return switch (registerCommand) {
            case RegisterAdminCommand registerAdminCommand -> registerAdmin(registerAdminCommand);
            case RegisterCustomerCommand registerCustomerCommand -> registerCustomer(registerCustomerCommand);
            case RegisterSellerCommand registerSellerCommand -> registerSeller(registerSellerCommand);
            default -> throw new RuntimeException("Unknown register command type");
        };
    }

    private AuthUserDto registerSeller(RegisterSellerCommand registerCommand) {
        Seller seller = modelMapper.map(registerCommand, Seller.class);
        seller.setPassword(passwordEncoder.encode(registerCommand.getPassword()));

        // Set seller role
        operationClaimService.addIfNotExistsOperationClaim(OperationClaims.seller.name());

        List<OperationClaim> claims = new ArrayList<>();
        claims.add(operationClaimService.getOperationClaimByName(OperationClaims.seller.name()));
        seller.setOperationClaims(claims);

        sellerRepository.save(seller);
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setToken(this.jwtService.generateToken(seller.getUsername(), getRoles(seller)));

        return authUserDto;
    }

    private AuthUserDto registerCustomer(RegisterCustomerCommand registerCommand) {
        Customer customer = modelMapper.map(registerCommand, Customer.class);
        customer.setPassword(passwordEncoder.encode(registerCommand.getPassword()));

        // Set customer role
        operationClaimService.addIfNotExistsOperationClaim(OperationClaims.customer.name());

        List<OperationClaim> claims = new ArrayList<>();
        claims.add(operationClaimService.getOperationClaimByName(OperationClaims.customer.name()));
        customer.setOperationClaims(claims);

        customerRepository.save(customer);
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setToken(this.jwtService.generateToken(customer.getUsername(), getRoles(customer)));

        return authUserDto;
    }

    private AuthUserDto registerAdmin(RegisterAdminCommand registerCommand) {
        Admin admin = modelMapper.map(registerCommand, Admin.class);
        admin.setPassword(passwordEncoder.encode(registerCommand.getPassword()));

        // Set admin role
        operationClaimService.addIfNotExistsOperationClaim(OperationClaims.admin.name());

        List<OperationClaim> claims = new ArrayList<>();
        claims.add(operationClaimService.getOperationClaimByName(OperationClaims.admin.name()));
        admin.setOperationClaims(claims);

        adminRepository.save(admin);
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setToken(this.jwtService.generateToken(admin.getUsername(), getRoles(admin)));

        return authUserDto;
    }
}
