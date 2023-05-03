package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.OrderDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Order;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.UserRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.utils.DateFormatter;

import java.util.Set;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    protected OrderMapper(ModelMapper mapper,
                          FilmRepository filmRepository,
                          UserRepository userRepository) {
        super(mapper, Order.class, OrderDTO.class);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDTO::setFilmId)).setPostConverter(toDtoConverter());
    }

    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
        //destination.setRentDate(DateFormatter.formatStringToDate(source.getRentDate()));
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
    }

    @Override
    protected Set<Long> getIds(Order entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}
