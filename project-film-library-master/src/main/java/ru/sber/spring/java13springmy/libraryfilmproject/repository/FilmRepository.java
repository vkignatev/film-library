package ru.sber.spring.java13springmy.libraryfilmproject.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;


@Repository
public interface FilmRepository
      extends GenericRepository<Film> {
}
