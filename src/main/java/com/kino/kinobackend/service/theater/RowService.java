package com.kino.kinobackend.service.theater;

import com.kino.kinobackend.model.theater.Row;
import com.kino.kinobackend.repository.theater.RowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RowService {

    private final RowRepository repository;

    public List<Row> getAll(){
        return this.repository.findAll();
    }

    public Optional<Row> findById(int id){
        return this.repository.findById(id);
    }


}
