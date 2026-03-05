package com.kino.kinobackend.service.booking;

import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.repository.booking.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowingService {

    private final ShowingRepository repository;

    public ShowingService(ShowingRepository repository) {
        this.repository = repository;
    }

    public List<Showing> getAll() {
        return this.repository.findAll();
    }

    public Optional<Showing> getById(int id) {
        return this.repository.findById(id);
    }

    public Optional <List<Showing>> getByMovieId(int movieId) {
        return this.repository.findByMovie_Id(movieId);
    }
}
