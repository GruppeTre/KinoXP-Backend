package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
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


}
