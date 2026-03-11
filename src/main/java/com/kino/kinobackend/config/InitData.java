package com.kino.kinobackend.config;

import com.kino.kinobackend.model.booking.Reservation;
import com.kino.kinobackend.model.booking.Showing;
import com.kino.kinobackend.model.booking.Status;
import com.kino.kinobackend.model.movie.Genre;
import com.kino.kinobackend.model.movie.Movie;
import com.kino.kinobackend.model.movie.Rating;
import com.kino.kinobackend.model.theater.Row;
import com.kino.kinobackend.model.theater.Seat;
import com.kino.kinobackend.model.theater.SeatType;
import com.kino.kinobackend.model.theater.Theater;
import com.kino.kinobackend.repository.movie.GenreRepository;
import com.kino.kinobackend.repository.movie.MovieRepository;
import com.kino.kinobackend.repository.movie.RatingRepository;
import com.kino.kinobackend.service.booking.ReservationService;
import com.kino.kinobackend.service.booking.ShowingService;
import com.kino.kinobackend.service.theater.RowService;
import com.kino.kinobackend.service.theater.SeatService;
import com.kino.kinobackend.service.theater.TheaterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitData implements CommandLineRunner {

    private final RowService rowService;
    private final SeatService seatService;
    private final ReservationService reservationService;
    private final ShowingService showingService;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;
    private final TheaterService theaterService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initializeTheater();
        initializeMovies();
        initializeShowings();
        initializeReservations();
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

    private void initializeShowings() {

        List<Theater> theaters = theaterService.findAll();
        List<Movie> movies = movieRepository.findAll();

        //Showings for today
        Showing showing1 = new Showing();
        showing1.setPrice(95);
        showing1.setMovie(movies.getFirst());
        showing1.setTheater(theaters.getFirst());
        showing1.setTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30)));

        Showing showing3 = new Showing();
        showing3.setPrice(95);
        showing3.setMovie(movies.getFirst());
        showing3.setTheater(theaters.getFirst());
        showing3.setTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19,0)));

        //Showings for tomorrow
        Showing showing2 = new Showing();
        showing2.setPrice(95);
        showing2.setMovie(movies.getFirst());
        showing2.setTheater(theaters.getFirst());
        showing2.setTime(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(12, 30)));


        showingService.add(showing1);
        showingService.add(showing2);
        showingService.add(showing3);

    }

    private void initializeReservations() {

        //reservation 1
        //books seats 1-3 on the first row
        Showing showing = showingService.getAll().getFirst();
        Optional<Theater> theaterResult = theaterService.findById(showing.getTheater().getId());
        if (theaterResult.isEmpty()) {
            System.out.println("Could not find theater with id " + showing.getTheater().getId());
            return;
        }

        Theater theater = theaterResult.get();

        List<Seat> seatsToReserve= theater.getRows().getFirst().getSeats().subList(0,4);
        //showing.getTheater().getRows().getFirst().getSeats().subList(0,4);

        Reservation res1 = new Reservation();
        res1.setStatus(Status.RESERVED);
        res1.setShowing(showing);
        res1.setSeats(seatsToReserve);
        res1.setName("Jørgen");
        res1.setEmail("Jorgen@test.dk");
        res1.setPhone_number("11111111");
        res1.setCreated_at(LocalDateTime.now().minusDays(1));

        //reservation 2
        //books seats 1-3 on the last row
        seatsToReserve= theater.getRows().getLast().getSeats().subList(0,4);

        Reservation res2 = new Reservation();
        res2.setStatus(Status.RESERVED);
        res2.setShowing(showing);
        res2.setSeats(seatsToReserve);
        res2.setName("Erik");
        res2.setEmail("Erik@test.dk");
        res2.setPhone_number("22222222");
        res2.setCreated_at(LocalDateTime.now().minusDays(2));

        reservationService.add(res1);
        reservationService.add(res2);
    }
}
