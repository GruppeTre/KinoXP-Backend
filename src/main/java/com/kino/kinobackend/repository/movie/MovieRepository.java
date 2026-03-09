package com.kino.kinobackend.repository.movie;

import com.kino.kinobackend.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findAllByPremiereAfter(LocalDate premiereAfter);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "JOIN Showing s ON s.movie = m " +
            "WHERE m.premiere <= CURRENT_DATE")
    List<Movie> findAllRunning();
}
