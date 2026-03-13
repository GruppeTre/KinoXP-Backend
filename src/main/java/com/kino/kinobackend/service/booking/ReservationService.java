package com.kino.kinobackend.service.booking;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
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

    public boolean hasReservations(int showingId) {
        Optional<List<Reservation>> reservations = repository.findAllByShowing_Id(showingId);

        System.out.println("Showing ID: " + showingId);
        System.out.println("Reservations fundet: " + reservations);

        return reservations.isPresent() && !reservations.get().isEmpty();
    }


   public List<Reservation> getReservedReservations(){
        return repository.findAllByStatus(Status.RESERVED).stream().filter(reservation -> {
            Showing showing = reservation.getShowing();
            return showing != null && !showing.getTime().toLocalDate().isBefore(LocalDate.now());
        }).toList();
   }

   public Reservation updateStatus(int id, Status status) {
        Reservation reservation = repository.findById(id).orElseThrow(()-> new RuntimeException("Reservationen er ikke fundet "));
        reservation.setStatus(status);
        return repository.save(reservation);
   }
}
