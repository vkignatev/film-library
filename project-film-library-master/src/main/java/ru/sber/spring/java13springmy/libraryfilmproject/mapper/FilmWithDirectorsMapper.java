package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.FilmWithDirectorsDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Film;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.DirectorRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmWithDirectorsMapper extends GenericMapper<Film, FilmWithDirectorsDTO> {
    private final DirectorRepository directorRepository;

    protected FilmWithDirectorsMapper(ModelMapper modelMapper, DirectorRepository directorRepository) {
        super(modelMapper, Film.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Film destination) {
        destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithDirectorsDTO destination) {
        destination.setDirectorIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Film entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
