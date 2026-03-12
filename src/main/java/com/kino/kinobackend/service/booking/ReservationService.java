package com.kino.kinobackend.service.booking;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.repository.booking.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    
    private final ReservationRepository repository;
    
    public ReservationService (ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getAll() {
        return this.repository.findAll();
    }
    public Optional<Reservation> getById(int id) {
        return this.repository.findById(id);
    }

    public Optional<List<Reservation>> getAllByShowingId(int showingId) {
        return this.repository.findAllByShowing_Id(showingId);
    }

    public Optional<List<Reservation>> getByEmail(String email) {
        return this.repository.findAllByEmail(email);
    }

    public Reservation add(Reservation reservation) {
        return this.repository.save(reservation);
    }

    public Optional<Reservation> update(Reservation reservation) {
        //check if reservation exists
        if (!repository.existsById(reservation.getId())) {
            return Optional.empty();
        }

        //update reservation and save it
        return Optional.of(repository.save(reservation));
    }
}
