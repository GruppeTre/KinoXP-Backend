package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.service.booking.ReservationService;
import com.kino.kinobackend.service.booking.ShowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final ReservationService reservationService;
    private final ShowingService showingService;

    public BookingController(ReservationService reservationService, ShowingService showingService) {
        this.reservationService = reservationService;
        this.showingService = showingService;
    }

/*
    GET ENDPOINTS
 */

    //reservation
    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAll();

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Optional<Reservation> result = reservationService.getById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result.get());
    }

    // showing
    @GetMapping("/showing")
    public ResponseEntity<List<Showing>> getAllShowings() {
        List<Showing> showings = showingService.getAll();

        return ResponseEntity.ok(showings);
    }

}
