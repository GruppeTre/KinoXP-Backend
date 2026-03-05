package com.kino.kinobackend.repository.theater;

import com.kino.kinobackend.model.theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository <Theater, Integer>{
}
