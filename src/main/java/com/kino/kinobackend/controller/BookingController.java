package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.service.booking.ReservationService;
import com.kino.kinobackend.service.booking.ShowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
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

    @GetMapping(value = "/reservation", params = "showingId")
    public ResponseEntity<List<Reservation>> getAllReservationsByShowingId(@RequestParam int showingId) {
        Optional<List<Reservation>> result = reservationService.getAllByShowingId(showingId);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result.get());
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


    @GetMapping(value = "/showing", params = "movieId")
    public ResponseEntity<List<Showing>> getAllShowingsByMovieId(@RequestParam int movieId) {
        Optional<List<Showing>> result = showingService.getByMovieId(movieId);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result.get());
    }

    //getShowingById
    @GetMapping("/showing/{id}")
    public ResponseEntity<Showing> getShowingById(@PathVariable int id){
        Optional<Showing> result = showingService.getById(id);

        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result.get());
    }

/*
    POST ENDPOINTS
*/

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Reservation created = reservationService.add(reservation);

        return ResponseEntity.ok(created);
    }

}
