package com.kino.kinobackend.model.booking;

import com.kino.kinobackend.model.theater.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private LocalDateTime created_at;
    private String phone_number;
    private String email;
    private String name;

    @ManyToOne
    private Showing showing;

    @OneToMany
    private List<Seat> seats;
}
