package ru.sber.spring.java13springmy.libraryfilmproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "orders_seq", allocationSize = 1)

public class Order extends  GenericModel{

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_RENT_FILM_INFO_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "FK_ORDERS_INFO_FILM"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "purchase")
    private boolean purchase;
}


