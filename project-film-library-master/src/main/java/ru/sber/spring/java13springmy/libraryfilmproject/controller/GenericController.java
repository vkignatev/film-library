package ru.sber.spring.java13springmy.libraryfilmproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.GenericDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.service.GenericService;

import java.util.List;

// http://localhost:9090/api/rest/swagger-ui/index.html#/

@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDTO> {

    private final GenericService<T, N> service;

    public GenericController(GenericService<T, N> genericService) {
        this.service = genericService;
    }

    @Operation(description = "Получить запись по ID", method = "getOneById")
    @RequestMapping(value = "/getById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> getById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOne(id));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @Operation(description = "Создать", method = "create")
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> create(@RequestBody N newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(newEntity));
    }

    @Operation(description = "Обновить", method = "update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<N> update(@RequestBody N updatedEntity,
                                    @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(updatedEntity));
    }

    //@RequestParam: localhost:9090/api/rest/films/deleteFilm?id=1
    //@PathVariable: localhost:9090/api/rest/films/deleteFilm/1
    @Operation(description = "Удалить", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}
