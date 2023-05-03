package ru.sber.spring.java13springmy.libraryfilmproject.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;

@Repository
public interface DirectorRepository
      extends GenericRepository<Director> {
}
