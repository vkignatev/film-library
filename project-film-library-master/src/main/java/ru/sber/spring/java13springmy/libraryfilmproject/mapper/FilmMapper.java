package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.DirectorRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.OrderInfoRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.utils.DateFormatter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final OrderInfoRepository orderInfoRepository;

    protected FilmMapper(ModelMapper modelMapper,
                         DirectorRepository directorRepository, OrderInfoRepository orderInfoRepository) {
        super(modelMapper, Film.class, FilmDTO.class);
        this.directorRepository = directorRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                .addMappings(m -> m.skip(FilmDTO::setDirectorIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmDTO::setOrdersIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Film::setOrders)).setPostConverter(toEntityConverter());

    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorIds())) {
            destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorIds())));
        } else {
            destination.setDirectors(Collections.emptySet());
        }
        if (!Objects.isNull(source.getOrdersIds())) {
            destination.setOrders(new HashSet<>(orderInfoRepository.findAllById(source.getOrdersIds())));
        } else {
            destination.setOrders(Collections.emptySet());
        }

    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {
        destination.setDirectorIds(getIds(source));
        destination.setOrdersIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Film entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getDirectors())
                ? null
                : entity.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
