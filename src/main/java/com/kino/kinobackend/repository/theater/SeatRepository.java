package com.kino.kinobackend.repository.theater;

import com.kino.kinobackend.model.theater.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {


}
