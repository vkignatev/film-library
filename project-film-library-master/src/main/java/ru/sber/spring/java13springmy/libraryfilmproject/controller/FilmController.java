package ru.sber.spring.java13springmy.libraryfilmproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.service.FilmService;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы",
        description = "Контроллер для работы с фильмами из фильмотеки")

public class FilmController
        extends GenericController<Film, FilmDTO> {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        super(filmService);
        this.filmService = filmService;
    }

    @Operation(description = "Добавить режиссёра к фильму", method = "addDirector")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addDirector(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {

        return ResponseEntity.status(HttpStatus.OK).body(filmService.addDirector(filmId, directorId));
    }
}
