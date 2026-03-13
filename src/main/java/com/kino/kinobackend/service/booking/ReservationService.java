package com.kino.kinobackend.service.booking;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.model.booking.Status;
import com.kino.kinobackend.repository.booking.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    
    private final ReservationRepository repository;
    private final ShowingService showingService;

    public ReservationService (ReservationRepository repository, ShowingService showingService) {
        this.repository = repository;
        this.showingService = showingService;
    }

    public List<Reservation> getAll() {
        return this.repository.findAll();
    }
    public Optional<Reservation> getById(int id) {
        return this.repository.findById(id);
    }

    public Optional<List<Reservation>> getByEmail(String email) {
        return this.repository.findAllByEmail(email);
    }

   public List<Reservation> getReservedReservations(){
        return repository.findAllByStatus(Status.RESERVED).stream().filter(reservation -> {
            Optional<Showing> showing = showingService.getById(reservation.getShowing_id());
            return showing.isPresent() && !showing.get().getTime().toLocalDate().isBefore(LocalDate.now());
        }).toList();
   }

   public Reservation updateStatus(int id, Status status) {
        Reservation reservation = repository.findById(id).orElseThrow(()-> new RuntimeException("Reservationen er ikke fundet "));
        reservation.setStatus(status);
        return repository.save(reservation);
   }
}
