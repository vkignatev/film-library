package ru.sber.spring.java13springmy.libraryfilmproject.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Order;

@Repository
public interface OrderInfoRepository
      extends GenericRepository<Order> {
}
