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

            theater.addRow(row);
        }

        theaterService.add(theater);
    }
}
