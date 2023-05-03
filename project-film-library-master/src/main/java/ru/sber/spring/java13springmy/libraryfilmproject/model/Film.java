package ru.sber.spring.java13springmy.libraryfilmproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "films_seq", allocationSize = 1)
public class Film extends GenericModel {

    @Column(name = "title", nullable = false)
    private String filmTitle;

    @Column(name = "premier_year", nullable = false)
    private Integer premierYear;

    @Column(name = "counrty", nullable = false)
    private String country;

    @Column(name = "genre", nullable = false)
    @Enumerated
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"), inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    private Set<Director> directors;

    @OneToMany(mappedBy = "film")
    private Set<Order> orders;
}
