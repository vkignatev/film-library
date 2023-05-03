package ru.sber.spring.java13springmy.libraryfilmproject.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.GenericDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.GenericMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.GenericRepository;

import java.util.List;

/**
 * Абстрактный сервис который хранит в себе реализацию CRUD операций по умолчанию
 * Если реализация отличная от того что представлено в этом классе,
 * то она переопределяется в сервисе для конкретной сущности
 *
 * @param <T> - Сущность с которой мы работаем
 * @param <N> - DTO, которую мы будем отдавать/принимать дальше
 */

@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository,
                             GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //всегда репозитории возвращвют модели!!!
    public List<N> listAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public N getOne(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Данных по заданному id: " + id + " не найдены")));
    }

    //контроллеры принимаю и отдают только DTO!!!
    public N create(N object) {
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public N update(N object) {
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
