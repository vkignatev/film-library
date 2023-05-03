package ru.sber.spring.java13springmy.libraryfilmproject.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmWithDirectorsDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.FilmMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.FilmWithDirectorsMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.DirectorRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {

    private final FilmRepository filmRepository;
    protected final DirectorRepository directorRepository;
    private final FilmWithDirectorsMapper filmWithDirectorsMapper;

    protected FilmService(FilmRepository filmRepository,
                          FilmMapper filmMapper,
                          DirectorRepository directorRepository,
                          FilmWithDirectorsMapper filmWithDirectorsMapper) {
        super(filmRepository, filmMapper);
        this.filmRepository = filmRepository;
        this.directorRepository=directorRepository;
        this.filmWithDirectorsMapper =filmWithDirectorsMapper;
    }

    //Сервис "Добавить режиссёра к фильму"
    public FilmDTO addDirector(Long filmId, Long directorId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(()->new NotFoundException("Фильм с таким ID не найден"));
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Режиссёр с таким ID не найден"));
        film.getDirectors().add(director);
        return mapper.toDTO(filmRepository.save(film));
    }

    public List<FilmWithDirectorsDTO> getAllFilmsWithDirectors() {
        return filmWithDirectorsMapper.toDTOs(filmRepository.findAll());
    }
}
