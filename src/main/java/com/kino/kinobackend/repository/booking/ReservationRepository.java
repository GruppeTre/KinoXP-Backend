package com.kino.kinobackend.repository.booking;

import com.kino.kinobackend.model.booking.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public Optional<List<Reservation>> findAllByEmail(String mail);
}
