package ru.sber.spring.java13springmy.libraryfilmproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "directors")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "directors_seq", allocationSize = 1)
public class Director extends GenericModel {
    @Column(name = "directors_fio", nullable = false)
    private String directorFio;

    @Column(name = "position")
    private Integer position;

    @ManyToMany(mappedBy = "directors")
    private Set<Film> films;
}
