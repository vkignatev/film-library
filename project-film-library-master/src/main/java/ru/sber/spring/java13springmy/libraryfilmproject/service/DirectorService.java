package ru.sber.spring.java13springmy.libraryfilmproject.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.DirectorDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.DirectorMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.DirectorRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {

    protected final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    protected DirectorService(DirectorMapper directorMapper, DirectorRepository directorRepository, FilmRepository filmRepository) {
        super(directorRepository, directorMapper);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }

    //Сервис "Добавить фильм к режиссёру"
    public DirectorDTO addFilm(Long directorId, Long filmId) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException("Режиссёр с таким ID не найден"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм с таким ID не найден"));
        director.getFilms().add(film);
        return mapper.toDTO(directorRepository.save(director));
    }
}
