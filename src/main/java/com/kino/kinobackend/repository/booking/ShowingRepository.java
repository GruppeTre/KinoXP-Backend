package com.kino.kinobackend.repository.booking;

import com.kino.kinobackend.model.booking.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    public Optional<List<Showing>> findByMovie_Id(int id);
}
