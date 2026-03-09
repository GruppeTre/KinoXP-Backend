package com.kino.kinobackend.init;

import com.kino.kinobackend.model.movie.Genre;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.model.movie.Rating;
import com.kino.kinobackend.repository.movie.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            MovieRepository movieRepo
    ) {
        return args -> {

            // --- Create Ratings ---
            Rating pg13 = new Rating();
            pg13.setName("Parents Strongly Cautioned");
            pg13.setAbbreviation("PG-13");


            // --- Create Genres ---
            Genre action = new Genre();
            action.setName("Action");

            Genre sciFi = new Genre();
            sciFi.setName("Sci-Fi");

            Genre drama = new Genre();
            drama.setName("Drama");


            // --- Create Movie ---
            Movie movie = new Movie();
            movie.setTitle("Inception");
            movie.setImgHref("https://example.com/inception.jpg");
            movie.setDescription("A thief enters dreams to steal secrets.");
            movie.setDirector("Christopher Nolan");
            movie.setPremiere(LocalDate.of(2027, 7, 16));

            movie.setRating(pg13); // ManyToOne

            movie.setGenres(Set.of(action, sciFi, drama)); // ManyToMany

            movieRepo.save(movie);

            System.out.println("Sample data inserted into MySQL");
        };
    }
}