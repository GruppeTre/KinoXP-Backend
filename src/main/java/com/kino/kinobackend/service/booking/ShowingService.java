package com.kino.kinobackend.service.booking;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.repository.booking.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowingService {

    private final ShowingRepository repository;
    private final ReservationService reservationService;

    public ShowingService(ShowingRepository repository, ReservationService reservationService) {
        this.repository = repository;
        this.reservationService = reservationService;
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

    public Showing add(Showing showing) {
        return this.repository.save(showing);
    }

    public Showing update(Showing showing) {
        if (showing.getId() == 0 || repository.findById(showing.getId()).isEmpty()) {
            throw new IllegalArgumentException("Visning findes ikke med id: " + showing.getId());
        }
        return repository.save(showing);
    }

    public boolean delete(int showingId) {

        if (reservationService.hasReservations(showingId)) {
            throw new IllegalStateException("Kan ikke slette visning med reserveringer");
        }

        repository.deleteById(showingId);
        return true;
    }


}
