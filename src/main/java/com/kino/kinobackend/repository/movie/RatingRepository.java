package com.kino.kinobackend.repository.movie;

import com.kino.kinobackend.model.movie.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
