package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.GenericDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Configuration
@Slf4j
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO>
        implements Mapper<E, D> {
    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected GenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    // если dto пустое вернем null, если нет то вернет entuty class
    public E toEntity(D dto) {
        log.info("GenericMapper метод public E toEntity(D dto)");
        log.info(dto.toString());
        return Objects.isNull(dto)
                ? null
                : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDTO(E entity) {
       // log.info(entity.toString());
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    @Override
    public List<D> toDTOs(List<E> entites) {
        return entites.stream().map(this::toDTO).toList();
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificFields(D source, E destination);

    protected abstract void mapSpecificFields(E source, D destination);

    protected abstract Set<Long> getIds(E entity);
}
