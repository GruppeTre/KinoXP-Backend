package com.kino.kinobackend.model.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String title;
    private String imgHref;
    private String description;
    private String director;
    private LocalDate premiere;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Genre> genres;
}
