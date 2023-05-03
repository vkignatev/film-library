package ru.sber.spring.java13springmy.libraryfilmproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends GenericDTO {
    private UserDTO user;
    private FilmDTO film;
    private LocalDateTime rentDate;
    private Integer rentPeriod;
    private Boolean purchase;
    private Long filmId;
    private Long userId;
}
