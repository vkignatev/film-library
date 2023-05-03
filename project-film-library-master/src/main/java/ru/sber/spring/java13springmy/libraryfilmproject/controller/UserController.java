package ru.sber.spring.java13springmy.libraryfilmproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.UserDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.User;
import ru.sber.spring.java13springmy.libraryfilmproject.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями")
public class UserController
        extends GenericController<User, UserDTO> {

    public UserController(UserService userService) {
        super(userService);
    }

/*
    @Operation(description = "Получить список всех фильмов пользователя", method = "getAllFilms")
    @RequestMapping(value = "/getAllFilms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    /*
    public ResponseEntity<Set<FilmDTO>> addFilm(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(id));
    }
     */
}
