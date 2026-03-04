package com.kino.kinobackend.model.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private LocalDateTime premiere;

    @ManyToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

    @ManyToMany
    private Set<Genre> genres;
}
