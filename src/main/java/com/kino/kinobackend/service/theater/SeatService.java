package com.kino.kinobackend.service.theater;

import com.kino.kinobackend.model.theater.Seat;
import com.kino.kinobackend.repository.theater.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatService {

    private final SeatRepository repository;


    public List<Seat> getAll(){
        return this.repository.findAll();
    }

    public Optional<Seat> findById(int id){
        return this.repository.findById(id);
    }


}
