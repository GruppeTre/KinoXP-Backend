package com.kino.kinobackend.service.movie;

import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.repository.movie.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Optional<Movie> getById(int id) {
        return repository.findById(id);
    }

    public List<Movie> getUpcomingMovies() {

        LocalDate premiereAfter = LocalDate.now();

        return repository.findAllByPremiereAfter(premiereAfter);
    }

    public List<Movie> getRunningMovies() {
        return repository.findAllRunning();
    }
}
