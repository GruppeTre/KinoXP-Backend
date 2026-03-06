package com.kino.kinobackend.controller;

import com.kino.kinobackend.model.theater.Theater;
import com.kino.kinobackend.service.theater.RowService;
import com.kino.kinobackend.service.theater.SeatService;
import com.kino.kinobackend.service.theater.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/theater")
public class TheaterController {
    private final RowService rowService;
    private final SeatService seatService;
    private final TheaterService theaterService;

/*
    GET ENDPOINTS
*/

    @GetMapping("")
    public ResponseEntity<List<Theater>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable int id) {
        Optional<Theater> result = theaterService.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result.get());
    }

}
