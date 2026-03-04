package com.kino.kinobackend.model.booking;

import com.kino.kinobackend.model.theater.Seat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private LocalDateTime created_at;
    private int phone_number;
    private String email;
    private String name;
    private int showing_id;
    private List<Seat> seats;
}
