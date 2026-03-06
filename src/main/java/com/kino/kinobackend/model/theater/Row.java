package com.kino.kinobackend.model.theater;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "seating_row")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        seat.setRow(this);
    }

    public void removeSeat(Seat seat) {
        this.seats.remove(seat);
        seat.setRow(null);
    }

}
