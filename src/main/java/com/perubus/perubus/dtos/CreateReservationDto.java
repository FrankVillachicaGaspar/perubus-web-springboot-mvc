package com.perubus.perubus.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReservationDto {
    @NotNull
    public int tripId;

    @NotNull
    public int seatId;
}
