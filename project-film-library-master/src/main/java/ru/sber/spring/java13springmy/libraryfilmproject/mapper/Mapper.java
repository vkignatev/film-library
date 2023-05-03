package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import ru.sber.spring.java13springmy.libraryfilmproject.dto.GenericDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);
}
