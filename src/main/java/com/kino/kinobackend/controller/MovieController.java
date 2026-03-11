package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.repository.movie.MovieRepository;
import com.kino.kinobackend.service.movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {


    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    // GET ENDPOINTS
    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.getAllMovies();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/running")
    public ResponseEntity<List<Movie>> getRunningMovies() {
        List<Movie> movies = movieService.getRunningMovies();

        return ResponseEntity.ok(movies);
    }


    @GetMapping("/upcoming")
    public ResponseEntity<List<Movie>> getUpcomingMovies() {
        List<Movie> movies = movieService.getUpcomingMovies();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
       Optional<Movie> movie = movieRepository.findById(id);

       if (movie.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.ok(movie.get());
    }

}
