package ru.sber.spring.java13springmy.libraryfilmproject.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmWithDirectorsDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.service.FilmService;

import java.util.List;


@Controller
@RequestMapping("films")
public class MVCFilmController {
    private final FilmService filmService;

    public MVCFilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<FilmWithDirectorsDTO> result = filmService.getAllFilmsWithDirectors();
        model.addAttribute("films", result);
        // model.addAttribute("films", result);
        return "films/viewAllFilms";
    }

    //Рисует форму создания фильма
    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    // Примит данные о созданном фильме и передаст в БД
    // Потом вернёт нас на страницу со всеми фильмами
    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }
}
