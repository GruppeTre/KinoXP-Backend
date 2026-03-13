package com.kino.kinobackend.model.booking;

import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.model.theater.Theater;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    private Theater theater;
}
