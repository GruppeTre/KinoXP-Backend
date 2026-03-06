package com.kino.kinobackend.service.theater;

import com.kino.kinobackend.model.theater.Theater;
import com.kino.kinobackend.repository.theater.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    private final TheaterRepository repository;

    public TheaterService(TheaterRepository repository){
        this.repository = repository;
    }

    public List<Theater> findAll(){
        return this.repository.findAll();
    }

    public Optional<Theater> findById(int id){
        return this.repository.findById(id);
    }

    public Theater add(Theater theater) {
        return this.repository.save(theater);
    }
}
