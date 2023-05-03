package ru.sber.spring.java13springmy.libraryfilmproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.OrderDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Order;
import ru.sber.spring.java13springmy.libraryfilmproject.service.OrderService;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Получение фильма",
        description = "Контроллер для работы с получением фильмов пользователям")
public class OrderFilmController
        extends GenericController<Order, OrderDTO> {
    private final OrderService orderService;

    public OrderFilmController(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }

    @Operation(description = "Взять фильм в аренду (купить)", method = "create")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.orderFilm(newEntity));
    }
}
