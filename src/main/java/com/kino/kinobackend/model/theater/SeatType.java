package com.kino.kinobackend.model.theater;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatType {
    NORMAL("normal", 0.0);

    private final String type;
    private final double priceModifier;
}
