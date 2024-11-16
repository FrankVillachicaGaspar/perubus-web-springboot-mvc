package com.perubus.perubus.controllers;

import com.perubus.perubus.services.impl.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips")
    public String trips(Model model) {
        var trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "trips/trips";
    }
}
