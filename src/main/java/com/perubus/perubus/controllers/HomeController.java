package com.perubus.perubus.controllers;

import com.perubus.perubus.services.impl.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final TripService tripService;

    @Autowired
    public HomeController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping()
    public String homeRedirect() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        var trips = tripService.getTop3Trips();
        model.addAttribute("trips", trips);
        return "home";
    }
}
