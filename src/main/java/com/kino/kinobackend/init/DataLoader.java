package com.kino.kinobackend.init;

import com.kino.kinobackend.model.movie.Genre;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.model.movie.Rating;
import com.kino.kinobackend.repository.movie.GenreRepository;
import com.kino.kinobackend.repository.movie.MovieRepository;
import com.kino.kinobackend.repository.movie.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            MovieRepository movieRepo, GenreRepository genreRepo, RatingRepository ratingRepo
    ) {
        return args -> {

            // --- Create Ratings ---
            Rating pg13 = new Rating();
            pg13.setName("Parents Strongly Cautioned");
            pg13.setAbbreviation("PG-13");

            ratingRepo.save(pg13);



            // --- Create Genres ---
            Genre action = new Genre();
            action.setName("Action");
            genreRepo.save(action);

            Genre sciFi = new Genre();
            sciFi.setName("Sci-Fi");
            genreRepo.save(sciFi);

            Genre drama = new Genre();
            drama.setName("Drama");
            genreRepo.save(drama);


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

            Movie movie1 = new Movie();
            movie1.setTitle("Narnia");
            movie1.setImgHref("http://example.com/narnia.jpg");
            movie1.setDescription("A group of children enters a closet");
            movie1.setDirector("Who Knows This?");
            movie1.setPremiere(LocalDate.of(2026, 6, 10));

            movie1.setRating(pg13);

            movie1.setGenres(Set.of(action, drama, sciFi));

            movieRepo.save(movie1);
        };
    }
}