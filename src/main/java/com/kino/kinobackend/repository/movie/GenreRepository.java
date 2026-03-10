package com.kino.kinobackend.repository.movie;

import com.kino.kinobackend.model.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
