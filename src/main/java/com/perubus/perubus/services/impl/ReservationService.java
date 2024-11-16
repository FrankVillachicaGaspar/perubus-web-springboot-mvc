package com.perubus.perubus.services.impl;

import com.perubus.perubus.dtos.CompleteReservationDto;
import com.perubus.perubus.dtos.CreateReservationDto;
import com.perubus.perubus.models.Reservation;
import com.perubus.perubus.models.Seat;
import com.perubus.perubus.models.Trip;
import com.perubus.perubus.repositories.IReservationRepository;
import com.perubus.perubus.repositories.ISeatRepository;
import com.perubus.perubus.repositories.ITripRepository;
import com.perubus.perubus.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    @Value("${reservation.blockTime.minutes}")
    private int blockTimeMinutes;
    private final IReservationRepository reservationRepository;
    private final ISeatRepository seatRepository;
    private final ITripRepository tripRepository;

    @Autowired
    public ReservationService(
            IReservationRepository reservationRepository,
            ISeatRepository seatRepository,
            ITripRepository tripRepository) {

        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Reservation createReservation(CreateReservationDto createReservationDto) {
        Seat seat = seatRepository.findById((long) createReservationDto.seatId).orElse(null);
        Trip trip = tripRepository.findById((long) createReservationDto.tripId).orElse(null);

        if (seat == null || trip == null) return null;

        if (seat.getBlockTime() != null) {
            if (seat.getBlockTime().isAfter(LocalDateTime.now()))
                return null;
        }

        seat.setBlockTime(LocalDateTime.now().plusMinutes(blockTimeMinutes));
        seatRepository.save(seat);

        Reservation reservation = Reservation.builder()
                .seat(seat)
                .trip(trip)
                .status("pendiente")
                .build();

        reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Reservation completeReservation(CompleteReservationDto completeReservationDto) {
        var reservation = reservationRepository.findById((long) completeReservationDto.id).orElse(null);

        if (reservation == null) return null;

        reservation.setCustomerName(completeReservationDto.customerName);
        reservation.setCustomerEmail(completeReservationDto.customerEmail);
        reservation.setCustomerPhoneNumber(completeReservationDto.customerPhoneNumber);
        reservation.setCustomerDni(completeReservationDto.customerDni);
        reservation.setStatus("completado");

        reservation.getSeat().setBlockTime(
                reservation.getTrip().getArrivalTime()
        );

        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation findReservationById(int id) {

        return reservationRepository.findById((long) id).orElse(null);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservation.getSeat().setBlockTime(null);
        reservation.setStatus("cancelado");
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }
}
