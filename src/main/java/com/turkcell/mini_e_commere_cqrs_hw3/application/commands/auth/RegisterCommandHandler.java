package com.turkcell.mini_e_commere_cqrs_hw3.application.commands.auth;

import an.awesome.pipelinr.Command;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.entity.*;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.repository.*;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.OperationClaimService;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.user.AuthUserDto;
import com.turkcell.mini_e_commere_cqrs_hw3.enums.OperationClaims;
import com.turkcell.mini_e_commere_cqrs_hw3.rules.UserBusinessRules;
import com.turkcell.mini_e_commere_cqrs_hw3.core.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.turkcell.mini_e_commere_cqrs_hw3.domain.service.UserService.getRoles;

@Component
@RequiredArgsConstructor
public class RegisterCommandHandler implements Command.Handler<RegisterCommand, AuthUserDto> {
    private final UserBusinessRules userBusinessRules;
    private final JwtService jwtService;
    private final OperationClaimService operationClaimService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final CartRepository cartRepository;

    @Override
    public AuthUserDto handle(RegisterCommand registerCommand) {
        userBusinessRules.usernameMustNotExist(registerCommand.getUsername());

        return switch (registerCommand) {
            case RegisterAdminCommand registerAdminCommand ->
                registerUser(registerAdminCommand, Admin.class, OperationClaims.admin, adminRepository);
            case RegisterCustomerCommand registerCustomerCommand ->
                registerUser(registerCustomerCommand, Customer.class, OperationClaims.customer, customerRepository);
            case RegisterSellerCommand registerSellerCommand ->
                registerUser(registerSellerCommand, Seller.class, OperationClaims.seller, sellerRepository);
            default -> throw new IllegalArgumentException(
                    "Unknown register command type: " + registerCommand.getClass().getSimpleName());
        };
    }

    private <T extends User> AuthUserDto registerUser(RegisterCommand registerCommand, Class<T> userClass,
            OperationClaims operationClaim, JpaRepository<T, UUID> repository) {
        T user = modelMapper.map(registerCommand, userClass);
        user.setPassword(passwordEncoder.encode(registerCommand.getPassword()));

        List<OperationClaim> claims = createAndGetOperationClaims(operationClaim);
        user.setOperationClaims(claims);

        repository.save(user);

        // Create cart for customer
        if (user instanceof Customer customer) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cartRepository.save(cart);
        }

        return createAuthUserDto(user);
    }

    private List<OperationClaim> createAndGetOperationClaims(OperationClaims operationClaim) {
        String claimName = operationClaim.name();
        operationClaimService.createOperationClaimIfNotExists(claimName);

        List<OperationClaim> claims = new ArrayList<>();
        claims.add(operationClaimService.getByName(claimName));
        return claims;
    }

    private AuthUserDto createAuthUserDto(User user) {
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setToken(this.jwtService.generateToken(String.valueOf(user.getId()), getRoles(user)));
        return authUserDto;
    }
}
