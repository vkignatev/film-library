package ru.sber.spring.java13springmy.libraryfilmproject.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring.java13springmy.libraryfilmproject.model.User;

@Repository
public interface UserRepository
        extends GenericRepository<User> {
    //select * from users where login = ?
    //@Query(nativeQuery = true, value = "select * from users where login = :login")
    User findUserByLogin(String login);
    User findUserByEmail(String email);
}
