package ru.sber.spring.java13springmy.libraryfilmproject.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.OrderDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.OrderMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Order;
import ru.sber.spring.java13springmy.libraryfilmproject.model.User;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.OrderInfoRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.UserRepository;

import java.time.LocalDate;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {
    private OrderInfoRepository orderInfoRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;


    protected OrderService(OrderInfoRepository orderInfoRepository,
                           OrderMapper orderMapper,
                           UserRepository userRepository,
                           FilmRepository filmRepository) {
        super(orderInfoRepository, orderMapper);
        this.orderInfoRepository = orderInfoRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    //Сервис "Взять фильм в аренду (купить)"
    public OrderDTO orderFilm(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("Пользователь с таким ID не найден"));
        Film film = filmRepository.findById(orderDTO.getFilmId())
                .orElseThrow(() -> new NotFoundException("Фильм с таким ID не найден"));
        Order order = new Order();
        order.setFilm(film);
        order.setUser(user);
        //Покупка
        if (orderDTO.getPurchase())
            order.setPurchase(true);
        //Иначе аренда
        else {
            order.setPurchase(false);
            order.setRentDate(LocalDate.now().atStartOfDay());
            order.setRentPeriod(orderDTO.getRentPeriod());
        }
        orderInfoRepository.save(order);
        return mapper.toDTO(order);
    }
}
