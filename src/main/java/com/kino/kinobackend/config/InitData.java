package com.kino.kinobackend.config;

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
        //create row one
        Row rowOne = new Row();
        rowOne.setName("a");

        Seat rowOneSeatOne = new Seat();
        rowOneSeatOne.setName("2");
        rowOneSeatOne.setType(SeatType.NORMAL);

        Seat rowOneSeatTwo = new Seat();
        rowOneSeatTwo.setName("4");
        rowOneSeatTwo.setType(SeatType.NORMAL);

        rowOne.addSeat(rowOneSeatOne);
        rowOne.addSeat(rowOneSeatTwo);

        //create row two
        Row rowTwo = new Row();
        rowTwo.setName("B");

        Seat rowTwoSeatOne = new Seat();
        rowTwoSeatOne.setName("2");
        rowTwoSeatOne.setType(SeatType.NORMAL);

        Seat rowTwoSeatTwo = new Seat();
        rowTwoSeatTwo.setName("4");
        rowTwoSeatTwo.setType(SeatType.NORMAL);

        rowTwo.addSeat(rowTwoSeatOne);
        rowTwo.addSeat(rowTwoSeatTwo);

        //Create theater
        Theater theater = new Theater();
        theater.setName("Sal 1");
        theater.addRow(rowOne);
        theater.addRow(rowTwo);

        theaterService.add(theater);
    }
}
