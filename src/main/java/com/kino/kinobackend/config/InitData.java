package com.kino.kinobackend.config;

import com.kino.kinobackend.model.movie.Genre;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.model.movie.Rating;
import com.kino.kinobackend.model.theater.Row;
import com.kino.kinobackend.model.theater.Seat;
import com.kino.kinobackend.model.theater.SeatType;
import com.kino.kinobackend.model.theater.Theater;
import com.kino.kinobackend.service.theater.RowService;
import com.kino.kinobackend.service.theater.SeatService;
import com.kino.kinobackend.service.theater.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitData implements CommandLineRunner {

    private final RowService rowService;
    private final SeatService seatService;
    private final TheaterService theaterService;

    @Override
    public void run(String... args) throws Exception {
        initializeTheater();
    }

    private void initializeTheater() {
        Theater theater = new Theater();
        theater.setName("Sal 1");

        char[] rowNames = {'A', 'B', 'C', 'D'};

        for (int i = 0; i < 4; i++) {
            Row row = new Row();
            row.setName(String.valueOf(rowNames[i]));

            for (int j = 1; j <= 10; j++) {
                Seat seat = new Seat();
                seat.setName(String.valueOf(j));
                seat.setType(SeatType.NORMAL);

                row.addSeat(seat);
            }

            row.getSeats().getLast().setInoperable(true);
            row.getSeats().get(4).setInoperable(true);
            row.getSeats().getFirst().setInoperable(true);
            theater.addRow(row);
        }

        theaterService.add(theater);
    }

    public void initializeMovies() {

        Rating rating15 = new Rating();
        rating15.setName("15");
        rating15.setAbbreviation("15");

        Genre genre1 = new Genre();
        genre1.setName("ACTION");
        Genre genre2 = new Genre();
        genre2.setName("COMEDY");
        Genre genre3 = new Genre();
        genre3.setName("DRAMA");

        Set<Genre> set1 = new HashSet<>();
        set1.add(genre1);
        set1.add(genre3);

        Set<Genre> set2 = new HashSet<>();
        set2.add(genre2);

        Movie movie1 = new Movie();
        movie1.setTitle("The Dark Knight");
        movie1.setDirector("Christopher Nolan");
        movie1.setDescription("Batman cleans up in Gotham City");
        movie1.setPremiere(LocalDate.of(2025, 5, 10));
        movie1.setImgHref("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQkUywIUXDjHSQJIaNHYVs08osgBpF5Ot-xmB_omyEZeeRP9Xug");
        movie1.setRating(rating15);
        movie1.setGenres(set1);

        Movie movie2 = new Movie();
        movie2.setTitle("The Mask");
        movie2.setDirector("Chuck Russell");
        movie2.setDescription("A shy bank clerk discovers a magical mask");
        movie2.setPremiere(LocalDate.of(1994, 7, 29));
        movie2.setImgHref("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiR8_9_An2NlR4qpZE_eSXf0AMYLVX7G9gzRDRqWoyq8axhIhA");
        movie2.setRating(rating15);
        movie2.setGenres(set2);

        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);

        ratingRepository.save(rating15);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
    }
}
