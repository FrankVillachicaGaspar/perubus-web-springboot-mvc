package com.perubus.perubus.controllers;

import com.perubus.perubus.dtos.CompleteReservationDto;
import com.perubus.perubus.dtos.CreateReservationDto;
import com.perubus.perubus.services.impl.ReservationService;
import com.perubus.perubus.services.impl.SeatService;
import com.perubus.perubus.services.impl.TripService;
import com.perubus.perubus.services.impl.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class ReserveController {
    private final TripService tripService;
    private final VehicleService vehicleService;
    private final SeatService seatService;
    private final ReservationService reservationService;


    @Autowired
    public ReserveController(
            TripService tripService,
            VehicleService vehicleService,
            SeatService seatService,
            ReservationService reservationService) {

        this.tripService = tripService;
        this.vehicleService = vehicleService;
        this.seatService = seatService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reserve/trip/{tripId}")
    public String tripInfo(@PathVariable int tripId, Model model) {
        var trip = tripService.getTripById(tripId);

        if (trip == null) return "redirect:trips";

        var vehicle = vehicleService.getVehicleById(trip.id);

        if (vehicle == null) return "redirect:trips";

        var seats = seatService.getSeatsByVehicleId(vehicle.id);

        var reservation = new CreateReservationDto();

        reservation.tripId = tripId;

        model.addAttribute("trip", trip);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("seats", seats);
        model.addAttribute("reservation", reservation);

        return "reserve/reserve_trip";
    }

    @PostMapping("/reserve/new")
    public String newReserve(@Valid @ModelAttribute("reservation") CreateReservationDto reserveDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:trips";
        }

        var reservation = reservationService.createReservation(reserveDto);
        if (reservation == null) return "redirect:/reserve/trip/" + reserveDto.tripId;

        return "redirect:/reserve/" + reservation.getId();

    }

    @GetMapping("/reserve/{reserveId}")
    public String completeReservationForm(@PathVariable int reserveId, Model model) {
        var reservation = reservationService.findReservationById(reserveId);

        if (reservation.getSeat().getBlockTime() != null) {
            if (reservation.getSeat().getBlockTime().isBefore(LocalDateTime.now())) {
                reservationService.cancelReservation(reservation);
                return "redirect:/home";
            }
        }

        if (Objects.equals(reservation.getStatus(), "completado") ||
                Objects.equals(reservation.getStatus(), "cancelado"))
            return "redirect:/reserve/ready/" + reservation.getId();


        model.addAttribute("reservation", reservation);

        return "reserve/complete_reservation";
    }

    @PostMapping("/reserve/complete")
    public String completeReservation(
            @Valid @ModelAttribute("reservation") CompleteReservationDto completeReservationDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:trips";
        }

        var reservation = reservationService.completeReservation(completeReservationDto);
        return "redirect:/reserve/ready/" + reservation.getId();
    }

    @GetMapping("/reserve/ready/{reserveId}")
    public String readyReservationForm(@PathVariable int reserveId, Model model) {
        var reservation = reservationService.findReservationById(reserveId);

        if (!Objects.equals(reservation.getStatus(), "completado"))
            return "redirect:/home";

        model.addAttribute("reservation", reservation);

        return "reserve/ready_reservation";
    }

    @GetMapping("/reserve/cancel/{reserveId}")
    public String cancelReservation(@PathVariable int reserveId) {
        var reservation = reservationService.findReservationById(reserveId);
        reservationService.cancelReservation(reservation);
        return "redirect:/reserve/trip/" + reservation.getTrip().getId();
    }

    @GetMapping("/reserve")
    public String reserve(Model model) {
        var reservations = reservationService.getReservations();
        model.addAttribute("reservations", reservations);
        return "reserve/list";
    }

}
