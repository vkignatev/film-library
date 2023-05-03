package ru.sber.spring.java13springmy.libraryfilmproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Genre;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericDTO {

    //заполняем поля из model
    private String filmTitle;
    private Integer premierYear;
    private String country;
    private Genre genre;

    // set режисёров id, для связанности
    private Set<Long> directorIds;
    private Set<Long> ordersIds;
}
