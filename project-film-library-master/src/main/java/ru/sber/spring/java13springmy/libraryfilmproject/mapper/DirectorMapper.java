package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.DirectorDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Director, DirectorDTO> {
    private final FilmRepository filmRepository;

    protected DirectorMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorDTO.class)
                .addMappings(m -> m.skip(DirectorDTO::setFilmsIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(DirectorDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorDTO source, Director destination) {
        if (!Objects.isNull(source.getFilmsIds())) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
        } else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Director entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getFilms())
                ? null
                : entity.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

