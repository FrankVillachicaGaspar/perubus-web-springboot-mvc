package com.perubus.perubus.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompleteReservationDto {
    @NotNull
    public int id;

    @NotNull
    public String customerName;

    @NotNull
    public String customerEmail;

    @NotNull
    public String customerPhoneNumber;

    @NotNull
    public String customerDni;
}
