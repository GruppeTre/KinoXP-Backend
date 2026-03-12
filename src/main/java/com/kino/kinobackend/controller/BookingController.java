package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.service.booking.ReservationService;
import com.kino.kinobackend.service.booking.ShowingService;
import com.kino.kinobackend.service.movie.MovieService;
import com.kino.kinobackend.service.theater.TheaterService;
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
    private final MovieService movieService;
    private final TheaterService theaterService;

    public BookingController(ReservationService reservationService, ShowingService showingService, MovieService movieService, TheaterService theaterService) {
        this.reservationService = reservationService;
        this.showingService = showingService;
        this.movieService = movieService;
        this.theaterService = theaterService;
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

    @PostMapping("/showing")
    public ResponseEntity<Showing> addShowing(@RequestBody Showing showing) {
        if (showing == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Showing created = showingService.add(showing);

        return ResponseEntity.ok(created);
    }

/*
    PUT ENDPOINTS
*/

    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation, @PathVariable int id) {

        if (id != reservation.getId()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Reservation> result = this.reservationService.update(reservation);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/showing/{id}")
    public ResponseEntity<Showing> updateShowing(@PathVariable int id,
                                                 @RequestBody Showing showingData) {
        Optional<Showing> existing = showingService.getById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Showing showing = existing.get();

        showing.setTime(showingData.getTime());
        showing.setPrice(showingData.getPrice());

        if (showingData.getMovie() != null && showingData.getMovie().getId() != 0) {
            movieService.getById(showingData.getMovie().getId())
                    .ifPresent(showing::setMovie);
        }

        if (showingData.getTheater() != null && showingData.getTheater().getId() != 0) {
            theaterService.findById(showingData.getTheater().getId())
                    .ifPresent(showing::setTheater);
        }

        Showing updated = showingService.update(showing);
        return ResponseEntity.ok(updated);
    }

    //DELETE

    @DeleteMapping("/showing/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable int id) {
        try {
            showingService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}


