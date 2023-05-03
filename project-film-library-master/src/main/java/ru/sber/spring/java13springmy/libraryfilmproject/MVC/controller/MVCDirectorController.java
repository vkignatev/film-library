package ru.sber.spring.java13springmy.libraryfilmproject.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.DirectorDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmWithDirectorsDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.service.DirectorService;
import ru.sber.spring.java13springmy.libraryfilmproject.service.FilmService;

import java.util.List;

@Controller
@RequestMapping("directors")
public class MVCDirectorController {
    private final DirectorService directorService;

    public MVCDirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<DirectorDTO> result = directorService.listAll();
        model.addAttribute("directors", result);
        return "directors/viewAllDirectors";
    }

    //Рисует форму создания режиссёра
    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorForm") DirectorDTO directorDTO) {
        directorService.create(directorDTO);
        return "redirect:/directors";
    }
}
