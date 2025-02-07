package com.turkcell.mini_e_commere_cqrs_hw3.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany()
    @JoinTable(
            name="user_operation_claims",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="operation_claim_id")
    )
    private List<OperationClaim> operationClaims;
}
