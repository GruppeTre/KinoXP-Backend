package com.kino.kinobackend.model.theater;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String name;
    boolean inoperable;

    @ManyToOne
    @JoinColumn(name = "theater_row", referencedColumnName = "id")
    private Row row;

    @Enumerated(EnumType.ORDINAL)
    private SeatType type;

}
