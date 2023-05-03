package ru.sber.spring.java13springmy.libraryfilmproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends GenericDTO {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String phone;
    private String address;
    private String email;
    private LocalDateTime createdWhen;
    private RoleDTO roles;
    private Set<Long> ordersIds;
}
