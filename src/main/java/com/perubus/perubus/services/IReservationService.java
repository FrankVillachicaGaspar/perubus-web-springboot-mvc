package com.perubus.perubus.services;

import com.perubus.perubus.dtos.CompleteReservationDto;
import com.perubus.perubus.dtos.CreateReservationDto;
import com.perubus.perubus.models.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation createReservation(CreateReservationDto createReservationDto);
    Reservation completeReservation(CompleteReservationDto completeReservationDto);
    Reservation findReservationById(int id);
    void cancelReservation(Reservation reservation);
    List<Reservation> getReservations();
}
