package ru.sber.spring.java13springmy.libraryfilmproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.DirectorDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;
import ru.sber.spring.java13springmy.libraryfilmproject.service.DirectorService;

@RestController
@RequestMapping("/directors")
@Tag(name = "Режиссёр",
        description = "Контроллер для работы с режиссёрами фильмов")
public class DirectorController
        extends GenericController<Director, DirectorDTO> {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        super(directorService);
        this.directorService = directorService;
    }

    @Operation(description = "Добавить фильм к режиссёру", method = "addFilm")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilm(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        return ResponseEntity.status(HttpStatus.OK).body(directorService.addFilm(directorId, filmId));
    }
}


