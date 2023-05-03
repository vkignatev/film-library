package ru.sber.spring.java13springmy.libraryfilmproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail", columnNames = "email"),
                             @UniqueConstraint(name = "uniqueLogin", columnNames = "login")})

@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "users_seq", allocationSize = 1)
public class User
        extends GenericModel {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;

    @Column(name = "created_when")
    private LocalDateTime createdWhen;

    @OneToMany(mappedBy = "user")
    private Set<Order> Order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id",nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ROLES"))
    private Role roles;
}
